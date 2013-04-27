package wekaPlusGrails

import org.springframework.dao.DataIntegrityViolationException

import weka.core.converters.ConverterUtils.DataSource
import weka.core.Instances
import weka.classifiers.rules.JRip
import weka.classifiers.rules.PART
import weka.classifiers.trees.J48
import weka.classifiers.Classifier as WekaClassifier

class ClassifierController {

	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	
	
	static navigation = [
		group:'menuNavigation', title:'Classifier', action:'index', order: 2,
			subItems:[
				[title:'List' , action:'list', order: 10],
				[title:'Create', action:'create', order: 11]
			]
	]


	def index() {
		redirect(action: "list", params: params)
	}

	def list(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		[classifierInstanceList: Classifier.list(params), classifierInstanceTotal: Classifier.count()]
	}

	def create() {
		[classifierInstance: new Classifier(params)]
	}

	def save() {
		
		def classifierInstance = new Classifier(params)
		
		if(classifierInstance.save(flush: true)){
			def trainSampleSearched = TrainSample.get(classifierInstance.trainSample.id)
			buildClassifierModel(trainSampleSearched, classifierInstance)

		} else{
			render(view: "create", model: [classifierInstance: classifierInstance])
			return
		}


		
		flash.message = message(code: 'default.created.message', args: [message(code: 'classifier.label', default: 'Classifier'), classifierInstance.id])
		redirect(action: "show", id: classifierInstance.id)
	
	}

	def show(Long id) {
		def classifierInstance = Classifier.get(id)
		if (!classifierInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'classifier.label', default: 'Classifier'), id])
			redirect(action: "list")
			return
		}

		[classifierInstance: classifierInstance]
	}

	def edit(Long id) {
		def classifierInstance = Classifier.get(id)
		if (!classifierInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'classifier.label', default: 'Classifier'), id])
			redirect(action: "list")
			return
		}
		
		[classifierInstance: classifierInstance]
	}

	def update(Long id, Long version) {
		def classifierInstance = Classifier.get(id)
		if (!classifierInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'classifier.label', default: 'Classifier'), id])
			redirect(action: "list")
			return
		}

		if (version != null) {
			if (classifierInstance.version > version) {
				classifierInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
						  [message(code: 'classifier.label', default: 'Classifier')] as Object[],
						  "Another user has updated this Classifier while you were editing")
				render(view: "edit", model: [classifierInstance: classifierInstance])
				return
			}
		}

		classifierInstance.properties = params

		if(classifierInstance.save(flush: true)){
			def trainSampleSearched = TrainSample.get(classifierInstance.trainSample.id)
			buildClassifierModel(trainSampleSearched, classifierInstance)

		} else{
			render(view: "create", model: [classifierInstance: classifierInstance])
			return
		}

/*
		if (!classifierInstance.save(flush: true)) {
			render(view: "edit", model: [classifierInstance: classifierInstance])
			return
		}
*/
		flash.message = message(code: 'default.updated.message', args: [message(code: 'classifier.label', default: 'Classifier'), classifierInstance.id])
		redirect(action: "show", id: classifierInstance.id)
	}

	def delete(Long id) {
		def classifierInstance = Classifier.get(id)
		if (!classifierInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'classifier.label', default: 'Classifier'), id])
			redirect(action: "list")
			return
		}

		try {
			classifierInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'classifier.label', default: 'Classifier'), id])
			redirect(action: "list")
		}
		catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'classifier.label', default: 'Classifier'), id])
			redirect(action: "show", id: id)
		}
	}
 
	
	def buildClassifierModel(TrainSample trainSampleInstance, Classifier classifierInstance){
			
		def webAppRootPath = servletContext.getRealPath("/")
		
		def classifierModelPath = new File(webAppRootPath, "/classifierModel/"+classifierInstance.id)
		classifierModelPath.mkdirs()
			//clean directory
			classifierModelPath.traverse {
				it.delete()
			}
		
		def trainSampleFile = new File(webAppRootPath, "/trainSample/"+trainSampleInstance.id+"/"+trainSampleInstance.filename)
		def source = new DataSource(trainSampleFile.path)
		
		Instances data = source.getDataSet()
		
		if (data.classIndex() == -1)
			data.setClassIndex(data.numAttributes() - 1);  // Make the last attribute be the class: setting class attribute if the data format does not provide this information
		
		def model
		if(classifierInstance.typeClassifier.contains("j48")){
			model = new J48()
		}else if (classifierInstance.typeClassifier.contains("jrip")) {
			model = new JRip()
		}else if (classifierInstance.typeClassifier.contains("part")) {
			model = new PART()
		}
				
		model.buildClassifier(data)
		
		writeModelOutputText(model, classifierModelPath.path + "/", classifierInstance)

		def classifierModelFile = new File(
			classifierModelPath.path + "/" + "ClassifierModelFile-" + classifierInstance.typeClassifier + ".model")
		
		//Serialize
		weka.core.SerializationHelper.write(classifierModelFile.path, model)
		
		classifierInstance.filename = classifierModelFile.name
				
	}
	

	def writeModelOutputText(WekaClassifier model, String path, Classifier classifierInstance){

		def textFile = new File(path + "model.txt")
		
			if(textFile.exists()){
				textFile.delete()
			}
		
		textFile.write(model.toString())
		classifierInstance.modelTxt = textFile.name
		
	}
	
	
	def downloadClassifierModel(){
		
		def classifierSearched = Classifier.get(params.id)
		
		def webAppRootPath = servletContext.getRealPath("/")
		def file = new File(webAppRootPath, "/classifierModel/${params.id}/"+classifierSearched.filename)
				
		if (file.exists()) {
		   response.setContentType("application/octet-stream")
		   response.setHeader("Content-disposition", "attachment; filename=${file.name}")
		   response.outputStream << file.bytes
		   return
		} else {
			println ("file não existe ou não encontrado")
		}
	
	}
	
	def downloadModelTxt(){
		
		def classifierSearched = Classifier.get(params.id)
		
		def webAppRootPath = servletContext.getRealPath("/")
		def file = new File(webAppRootPath, "/classifierModel/${params.id}/"+classifierSearched.modelTxt)
						
		if (file.exists()) {
		   response.setContentType("text/plain")
		   response.setHeader("Content-disposition", "attachment; filename=${file.name}")
		   response.outputStream << file.bytes
		   return
		} else {
			println ("file não existe ou não encontrado")
		}
	
	}
	
	
}
