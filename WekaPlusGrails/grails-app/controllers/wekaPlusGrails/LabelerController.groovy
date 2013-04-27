package wekaPlusGrails


import org.springframework.dao.DataIntegrityViolationException
import org.springframework.web.multipart.commons.CommonsMultipartFile

import weka.classifiers.Classifier as WekaClassifier
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import groovy.io.FileType

class LabelerController {

	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	
	static navigation = [
		group:'menuNavigation', title:'Labeler', action:'index', order: 4,
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
		[labelerInstanceList: Labeler.list(params), labelerInstanceTotal: Labeler.count()]
	}

	def create() {
		[labelerInstance: new Labeler(params)]
	}

	def save() {
		def labelerInstance = new Labeler(params)
		
		def uploadedFile = request.getFile('unlabeledArffFilename')
		labelerInstance.unlabeledArffFilename = uploadedFile.originalFilename
				
		if(labelerInstance.save(flush: true)){
			
			saveFileUnlabeledTextField(labelerInstance)
			
			if(!uploadedFile.empty){
				saveFileUnlabeledArff(uploadedFile, labelerInstance)
			}
			
			labelInstances(labelerInstance)
			
			flash.message = message(code: 'default.created.message', args: [message(code: 'labeler.label', default: 'Labeler'), labelerInstance.id])
			redirect(action: "show", id: labelerInstance.id)
			
		} else {
			render(view: "create", model: [labelerInstance: labelerInstance])
			return
		}
		

	}

	def show(Long id) {
		def labelerInstance = Labeler.get(id)
		if (!labelerInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'labeler.label', default: 'Labeler'), id])
			redirect(action: "list")
			return
		}

		[labelerInstance: labelerInstance]
	}

	def edit(Long id) {
		def labelerInstance = Labeler.get(id)
		if (!labelerInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'labeler.label', default: 'Labeler'), id])
			redirect(action: "list")
			return
		}
		
		////-----------
		def webAppRootPath = servletContext.getRealPath("/")
		def FileUnlabeledTextField = new File(webAppRootPath, "/labeler/${params.id}/"+labelerInstance.unlabeledTextFieldFilename)
		def textInFile
		if (FileUnlabeledTextField.exists()) {
			textInFile = FileUnlabeledTextField.text
		}
		
		labelerInstance.unlabeledTextFieldFilename = textInFile
		///--

		[labelerInstance: labelerInstance]
	}

	def update(Long id, Long version) {
		def labelerInstance = Labeler.get(id)
		if (!labelerInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'labeler.label', default: 'Labeler'), id])
			redirect(action: "list")
			return
		}

		if (version != null) {
			if (labelerInstance.version > version) {
				labelerInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
						  [message(code: 'labeler.label', default: 'Labeler')] as Object[],
						  "Another user has updated this Labeler while you were editing")
				render(view: "edit", model: [labelerInstance: labelerInstance])
				return
			}
		}

		labelerInstance.properties = params
		
		

		def uploadedFile = request.getFile('unlabeledArffFilename')
		labelerInstance.unlabeledArffFilename = uploadedFile.originalFilename
				
		if(labelerInstance.save(flush: true)){
			
			saveFileUnlabeledTextField(labelerInstance)
			
			if(!uploadedFile.empty){
				saveFileUnlabeledArff(uploadedFile, labelerInstance)
			}
			
			labelInstances(labelerInstance)
			
			flash.message = message(code: 'default.updated.message', args: [message(code: 'labeler.label', default: 'Labeler'), labelerInstance.id])
			redirect(action: "show", id: labelerInstance.id)
			
		} else {
			render(view: "edit", model: [labelerInstance: labelerInstance])
			return
		}
		
		/*
		if (!labelerInstance.save(flush: true)) {
			render(view: "edit", model: [labelerInstance: labelerInstance])
			return
		}

		flash.message = message(code: 'default.updated.message', args: [message(code: 'labeler.label', default: 'Labeler'), labelerInstance.id])
		redirect(action: "show", id: labelerInstance.id)
		*/
		
		
	}

	def delete(Long id) {
		def labelerInstance = Labeler.get(id)
		if (!labelerInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'labeler.label', default: 'Labeler'), id])
			redirect(action: "list")
			return
		}

		try {
			labelerInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'labeler.label', default: 'Labeler'), id])
			redirect(action: "list")
		}
		catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'labeler.label', default: 'Labeler'), id])
			redirect(action: "show", id: id)
		}
	}
	
	
	def saveFileUnlabeledTextField(Labeler labelerInstance) {
		
		//make Path
		def webAppRootPath = servletContext.getRealPath("/")
		def labelerPath = new File(webAppRootPath, "/labeler/" + labelerInstance.id)
		labelerPath.mkdirs()
					
		//delete old files
		labelerPath.traverse(
			type:FileType.FILES,
			nameFilter:~/1-unlabeledTextField.*/
		) {
			it.delete()
		};
		
		//write in the file
		def arffUnlabeledTextField = new File(labelerPath.path, "/" + "1-unlabeledTextField.arff")
		 //arffUnlabeledTextField.write(labelerInstance.unlabeledTextFieldFilename)
		arffUnlabeledTextField.write(params.unlabeledTextFieldFilename)
		labelerInstance.unlabeledTextFieldFilename = arffUnlabeledTextField.name
		
	}
	
	
	def saveFileUnlabeledArff(CommonsMultipartFile uploadedFile, Labeler labelerInstance){
		
		//make Path
		def webAppRootPath = servletContext.getRealPath("/")
		def labelerPath = new File(webAppRootPath, "/labeler/" + labelerInstance.id)
		labelerPath.mkdirs()
					
		//delete old files
		labelerPath.traverse(
			type:FileType.FILES,
			nameFilter:~/2-unlabeledArffFile-.*/
		) {
			it.delete()
		};
		
		//write in the file
		uploadedFile.transferTo( new File( labelerPath, "/" + "2-unlabeledArffFile-" + uploadedFile.originalFilename))
		labelerInstance.unlabeledArffFilename = "2-unlabeledArffFile-" + uploadedFile.originalFilename
		
				
	}
		
	
	def downloadFileUnlabeledTextField(){
		
		def labelerSearched = Labeler.get(params.id)
		
		def webAppRootPath = servletContext.getRealPath("/")
		def file = new File(webAppRootPath, "/labeler/${params.id}/"+labelerSearched.unlabeledTextFieldFilename)
		
		if (file.exists()) {
		   response.setContentType("text/plain")
		   response.setHeader("Content-disposition", "attachment; filename=${file.name}")
		   response.outputStream << file.bytes
		   return
		   
		   
		} else {
			println ("file não existe ou não encontrado")
		}
	
	}


	def downloadFileUnlabeledArff(){
		
		def labelerSearched = Labeler.get(params.id)
		
		def webAppRootPath = servletContext.getRealPath("/")
		def file = new File(webAppRootPath, "/labeler/${params.id}/"+labelerSearched.unlabeledArffFilename)
		
		if (file.exists()) {
		   response.setContentType("text/plain")
		   response.setHeader("Content-disposition", "attachment; filename=${file.name}")
		   response.outputStream << file.bytes
		   return
		   
		   
		} else {
			println ("file não existe ou não encontrado")
		}
	
	}
	
	
	def downloadFileLabeledArff(){
		
		def labelerSearched = Labeler.get(params.id)
		
		def webAppRootPath = servletContext.getRealPath("/")
		def file = new File(webAppRootPath, "/labeler/${params.id}/"+labelerSearched.labeledArffFilename)
		
		if (file.exists()) {
		   response.setContentType("text/plain")
		   response.setHeader("Content-disposition", "attachment; filename=${file.name}")
		   response.outputStream << file.bytes
		   return
		   
		   
		} else {
			println ("file não existe ou não encontrado")
		}
	
	}
	
	
	def labelInstances(Labeler labelerInstance){
		
		
		//preload UnlabeledSet
		File inputTypeSelected
		def webAppRootPath = servletContext.getRealPath("/")
		def unlabeledFilePath = new File(webAppRootPath, "/labeler/" + labelerInstance.id)
		
		if(labelerInstance.typeInput.contains("textFieldInput")){
			inputTypeSelected = new File(unlabeledFilePath.path, "/" + "1-unlabeledTextField.arff")
		}else if (labelerInstance.typeInput.contains("arffFilenameInput")) {
			inputTypeSelected = new File(unlabeledFilePath, "/" + labelerInstance.unlabeledArffFilename)
		}
			
		//load UnlabeledSet
		
		DataSource sourceUnlabeled = new DataSource(inputTypeSelected.path)
		Instances unlabeledSet = sourceUnlabeled.getDataSet()
			if (unlabeledSet.classIndex() == -1)
				unlabeledSet.setClassIndex(unlabeledSet.numAttributes() - 1);  // Make the last attribute be the class: setting class attribute if the data format does not provide this information
		
		//create the future LabeledSet (initially a copy of UnlabeledSet)
		Instances labeledSet = new Instances(unlabeledSet)
		
		//take classifierModel trained
		def classifierModelSearched = Classifier.get(labelerInstance.classifier.id)
		def classifierModel = new File(webAppRootPath, "/classifierModel/" + classifierModelSearched.id + "/" + classifierModelSearched.filename)
		def classifierTrained = (WekaClassifier) weka.core.SerializationHelper.read(classifierModel.path)
	
		//labeling now
		println "unlabeledSet.numInstances(): " + unlabeledSet.numInstances()
		Double classifierLabel
		for(i in 0..unlabeledSet.numInstances()-1){
			classifierLabel = classifierTrained.classifyInstance(unlabeledSet.instance(i))
			labeledSet.instance(i).setClassValue(classifierLabel)
			////labeledSet.instance(i).setClassValue(unlabeledSet.classAttribute().value((int) classifierLabel))
			////println(classifierLabel + " -> " + unlabeledSet.classAttribute().value((int) classifierLabel))
		}
		
		println("falta pouco...")
	
		//save labeledSet
		saveFileLabeledArff(labeledSet, labelerInstance)
		
		
		

	}
	
	def saveFileLabeledArff(Instances labeledSet, Labeler labelerInstance){
		println("saveFileLabeledArff -------------")
		
		//make Path
		def webAppRootPath = servletContext.getRealPath("/")
		def labelerPath = new File(webAppRootPath, "/labeler/" + labelerInstance.id)
		labelerPath.mkdirs()
					
		//delete old files
		labelerPath.traverse(
			type:FileType.FILES,
			nameFilter:~/3-labeledArffFile.*/
		) {
			it.delete()
		}
				
		//write in the file
		def arffLabeledArffFile = new File(labelerPath.path, "/" + "3-labeledArffFile.arff")
		arffLabeledArffFile.write(labeledSet.toString())
		labelerInstance.labeledArffFilename = arffLabeledArffFile.name

		
	}
	
}

