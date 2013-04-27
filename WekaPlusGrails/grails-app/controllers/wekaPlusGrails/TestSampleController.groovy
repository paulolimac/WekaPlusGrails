package wekaPlusGrails

import org.springframework.dao.DataIntegrityViolationException

import weka.classifiers.Classifier as WekaClassifier
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.classifiers.Evaluation



class TestSampleController {

	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	
	static navigation = [
		group:'menuNavigation', title:'Test Sample', action:'index', order: 3,
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
		[testSampleInstanceList: TestSample.list(params), testSampleInstanceTotal: TestSample.count()]
	}

	def create() {
		[testSampleInstance: new TestSample(params)]
	}

	def save() {
		def testSampleInstance = new TestSample(params)
			   
		def uploadedFile = request.getFile('filename')
		testSampleInstance.filename = uploadedFile.originalFilename
				
		if(testSampleInstance.save(flush: true)){
			
			if(!uploadedFile.empty){
					//save arff test file
					def webAppRootPath = servletContext.getRealPath("/")
					def testSamplePath = new File(webAppRootPath, "/testSample/"+testSampleInstance.id)
					testSamplePath.mkdirs()
						//clean directory
						testSamplePath.traverse {
							it.delete()
						}
					uploadedFile.transferTo( new File( testSamplePath, uploadedFile.originalFilename))
					testSampleInstance.filename = uploadedFile.originalFilename
					
					//evaluate the model (testArff)
					def arffFileTest = new File(testSamplePath.path+"/"+uploadedFile.originalFilename)
					evaluateClassifierModel(arffFileTest, testSampleInstance)
				}
					
			flash.message = message(code: 'default.created.message', args: [message(code: 'testSample.label', default: 'TestSample'), testSampleInstance.id])
			redirect(action: "show", id: testSampleInstance.id)
			
		} else {
			render(view: "create", model: [testSampleInstance: testSampleInstance])
			return
		}
		
	}

	def show(Long id) {
		def testSampleInstance = TestSample.get(id)
		if (!testSampleInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'testSample.label', default: 'TestSample'), id])
			redirect(action: "list")
			return
		}

		[testSampleInstance: testSampleInstance]
	}

	def edit(Long id) {
		def testSampleInstance = TestSample.get(id)
		if (!testSampleInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'testSample.label', default: 'TestSample'), id])
			redirect(action: "list")
			return
		}

		[testSampleInstance: testSampleInstance]
	}

	def update(Long id, Long version) {
		def testSampleInstance = TestSample.get(id)
		if (!testSampleInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'testSample.label', default: 'TestSample'), id])
			redirect(action: "list")
			return
		}

		if (version != null) {
			if (testSampleInstance.version > version) {
				testSampleInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
						  [message(code: 'testSample.label', default: 'TestSample')] as Object[],
						  "Another user has updated this TestSample while you were editing")
				render(view: "edit", model: [testSampleInstance: testSampleInstance])
				return
			}
		}

		testSampleInstance.properties = params


		def uploadedFile = request.getFile('filename')
		testSampleInstance.filename = uploadedFile.originalFilename
				
		if(testSampleInstance.save(flush: true)){
			
			if(!uploadedFile.empty){
					//save arff test file
					def webAppRootPath = servletContext.getRealPath("/")
					def testSamplePath = new File(webAppRootPath, "/testSample/"+testSampleInstance.id)
					testSamplePath.mkdirs()
						//clean directory
						testSamplePath.traverse {
							it.delete()
						}
					uploadedFile.transferTo( new File( testSamplePath, uploadedFile.originalFilename))
					testSampleInstance.filename = uploadedFile.originalFilename
					
					//evaluate the model (testArff)
					def arffFileTest = new File(testSamplePath.path+"/"+uploadedFile.originalFilename)
					evaluateClassifierModel(arffFileTest, testSampleInstance)
				}
					
			flash.message = message(code: 'default.updated.message', args: [message(code: 'testSample.label', default: 'TestSample'), testSampleInstance.id])
			redirect(action: "show", id: testSampleInstance.id)
			
		} else {
			render(view: "edit", model: [testSampleInstance: testSampleInstance])
			return
		}


/*
		if (!testSampleInstance.save(flush: true)) {
			render(view: "edit", model: [testSampleInstance: testSampleInstance])
			return
		}

		flash.message = message(code: 'default.updated.message', args: [message(code: 'testSample.label', default: 'TestSample'), testSampleInstance.id])
		redirect(action: "show", id: testSampleInstance.id)

*/

	}

	def delete(Long id) {
		def testSampleInstance = TestSample.get(id)
		if (!testSampleInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'testSample.label', default: 'TestSample'), id])
			redirect(action: "list")
			return
		}

		try {
			testSampleInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'testSample.label', default: 'TestSample'), id])
			redirect(action: "list")
		}
		catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'testSample.label', default: 'TestSample'), id])
			redirect(action: "show", id: id)
		}
	}
	
	   def downloadFilename(){
		
		def testSampleSearched = TestSample.get(params.id)
		
		def webAppRootPath = servletContext.getRealPath("/")
		def file = new File(webAppRootPath, "/testSample/${params.id}/"+testSampleSearched.filename)
		
		println("${params.filename}")
		println(file.path)
		
		if (file.exists()) {
		   response.setContentType("text/plain")
		   response.setHeader("Content-disposition", "attachment; filename=${file.name}")
		   response.outputStream << file.bytes
		   return
		} else {
			println ("file não existe ou não encontrado")
		}
	
	}
	
	def downloadEvaluationTxt(){
		
		def testSampleSearched = TestSample.get(params.id)
		
		def webAppRootPath = servletContext.getRealPath("/")
		def file = new File(webAppRootPath, "/testSample/${params.id}/"+testSampleSearched.evaluationTxt)
		
		println("${params.evaluationTxt}")
		println(file.path)

		
		if (file.exists()) {
			//response.setContentType("application/octet-stream")
			response.setContentType("text/plain")
			response.setHeader("Content-disposition", "attachment; filename=${file.name}")
			   response.outputStream << file.bytes
			   return
		} else {
			println ("file não existe ou não encontrado")
		}
	
	}
 
	
	def evaluateClassifierModel(File testFileUploaded, TestSample testSampleInstance){
	
		//trainSet
		Classifier classifierInstance = Classifier.get(testSampleInstance.classifier.id)
		TrainSample trainSampleInstance = classifierInstance.trainSample
		def webAppRootPath = servletContext.getRealPath("/")
		def trainSampleFile = new File(webAppRootPath, "/trainSample/"+trainSampleInstance.id+"/"+trainSampleInstance.filename)
		
		DataSource sourceTrain = new DataSource(trainSampleFile.path)
		Instances trainSet = sourceTrain.getDataSet()
			if (trainSet.classIndex() == -1)
				trainSet.setClassIndex(trainSet.numAttributes() - 1);  // Make the last attribute be the class: setting class attribute if the data format does not provide this information
		
		//TestSet
		DataSource sourceTest = new DataSource(testFileUploaded.path)
		Instances testSet = sourceTest.getDataSet()
			if (testSet.classIndex() == -1)
				testSet.setClassIndex(testSet.numAttributes() - 1);  // Make the last attribute be the class: setting class attribute if the data format does not provide this information

				
		//Take and Deserialize the ClassifierModel trained
		def classifierModel = new File(webAppRootPath, "/classifierModel/" + classifierInstance.id + "/" + classifierInstance.filename)
		def classifierTrained = (WekaClassifier) weka.core.SerializationHelper.read(classifierModel.path)
		
		//evaluate the ClassifierModel with the trainSet and testSet
		def evaluation = new Evaluation(trainSet)
		evaluation.evaluateModel(classifierTrained, testSet)
		
		//Save the evaluation on the 'evaluationTxt'
		writeEvaluationTxtFile(evaluation, testFileUploaded.parent + "/", testSampleInstance)
		
	}
	
	
	def writeEvaluationTxtFile(Evaluation evaluationProduced, String pathOfEvaluationTxtFile, TestSample testSampleInstance){
		
		def textFile = new File(pathOfEvaluationTxtFile + "evaluation.txt")
		
			if(textFile.exists()){
				textFile.delete()
			}
		
		textFile.write(
			"\nResults of Evaluation\n"
			+ "=====================\n"
			+ "\n----- Test Definition -----\n"
			+ "		\ntestSampleInstance.name: " + testSampleInstance.name
			+ "		\ntestSampleInstance.description: " + testSampleInstance.description
			+ "		\ntestSampleInstance.filename: " + testSampleInstance.filename
			+ "\n\n"
			+ "\n----- Classifier Definition -----\n"
			+ "		\ntestSampleInstance.classifier.name: " + testSampleInstance.classifier.name
			+ "		\ntestSampleInstance.classifier.description: " + testSampleInstance.classifier.description
			+ "		\ntestSampleInstance.classifier.typeClassifier: " + testSampleInstance.classifier.typeClassifier
			+ "\n\n"
			+ "\n----- Evaluation -----\n"
			+ evaluationProduced.toSummaryString("\n----- Evaluation - Summary -----\n", false)
			+ evaluationProduced.toMatrixString("\n----- Evaluation - Confusion Matrix -----\n")
			+ evaluationProduced.toClassDetailsString("\n----- Evaluation - ClassDetail - Detailed Accuracy by Class -----\n")
			+ "\n\n\n=====================\n"
		)
		
		println(textFile.text)
		
		testSampleInstance.evaluationTxt = textFile.name
	
	}
	
	
}
