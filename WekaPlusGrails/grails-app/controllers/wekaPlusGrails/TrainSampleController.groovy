package wekaPlusGrails

import org.springframework.dao.DataIntegrityViolationException

class TrainSampleController {

	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	
	
	static navigation = [
		group:'menuNavigation', title:'Train Sample', action:'index', order: 1,
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
		[trainSampleInstanceList: TrainSample.list(params), trainSampleInstanceTotal: TrainSample.count()]
	}

	def create() {
		[trainSampleInstance: new TrainSample(params)]
	}

	def save() {
		def trainSampleInstance = new TrainSample(params)
				
		if(trainSampleInstance.save(flush: true)){
			
			def uploadedFile = request.getFile('arffFile')
				if(!uploadedFile.empty){
					def webAppRootPath = servletContext.getRealPath("/")
					def trainSamplePath = new File(webAppRootPath, "/trainSample/"+trainSampleInstance.id)
					trainSamplePath.mkdirs()
						//clean directory
						trainSamplePath.traverse {
							it.delete()
						}
					uploadedFile.transferTo( new File( trainSamplePath, uploadedFile.originalFilename))
					trainSampleInstance.filename = uploadedFile.originalFilename
				}
			println(trainSampleInstance.filename)
			flash.message = message(code: 'default.created.message', args: [message(code: 'trainSample.label', default: 'TrainSample'), trainSampleInstance.id])
			redirect(action: "show", id: trainSampleInstance.id)
			
		} else {
			render(view: "create", model: [trainSampleInstance: trainSampleInstance])
			return
		}
		
		
	}

	def show(Long id) {
		def trainSampleInstance = TrainSample.get(id)
		println(trainSampleInstance.filename)
		if (!trainSampleInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'trainSample.label', default: 'TrainSample'), id])
			redirect(action: "list")
			return
		}

		[trainSampleInstance: trainSampleInstance]
	}

	def edit(Long id) {
		def trainSampleInstance = TrainSample.get(id)
		if (!trainSampleInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'trainSample.label', default: 'TrainSample'), id])
			redirect(action: "list")
			return
		}

		[trainSampleInstance: trainSampleInstance]
	}

	def update(Long id, Long version) {
		def trainSampleInstance = TrainSample.get(id)
		if (!trainSampleInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'trainSample.label', default: 'TrainSample'), id])
			redirect(action: "list")
			return
		}

		if (version != null) {
			if (trainSampleInstance.version > version) {
				trainSampleInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
						  [message(code: 'trainSample.label', default: 'TrainSample')] as Object[],
						  "Another user has updated this TrainSample while you were editing")
				render(view: "edit", model: [trainSampleInstance: trainSampleInstance])
				return
			}
		}

		trainSampleInstance.properties = params

		if(trainSampleInstance.save(flush: true)){
			
			def uploadedFile = request.getFile('arffFile')
				if(!uploadedFile.empty){
					def webAppRootPath = servletContext.getRealPath("/")
					def trainSamplePath = new File(webAppRootPath, "/trainSample/"+trainSampleInstance.id)
					trainSamplePath.mkdirs()
						//clean directory
						trainSamplePath.traverse {
							it.delete()
						}
					uploadedFile.transferTo( new File( trainSamplePath, uploadedFile.originalFilename))
					trainSampleInstance.filename = uploadedFile.originalFilename
				}
			println(trainSampleInstance.filename)
		flash.message = message(code: 'default.updated.message', args: [message(code: 'trainSample.label', default: 'TrainSample'), trainSampleInstance.id])
		redirect(action: "show", id: trainSampleInstance.id)
			
		} else {
			render(view: "edit", model: [trainSampleInstance: trainSampleInstance])
			return
		}

	}

	def delete(Long id) {
		def trainSampleInstance = TrainSample.get(id)
		if (!trainSampleInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'trainSample.label', default: 'TrainSample'), id])
			redirect(action: "list")
			return
		}

		try {
			trainSampleInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'trainSample.label', default: 'TrainSample'), id])
			redirect(action: "list")
		}
		catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'trainSample.label', default: 'TrainSample'), id])
			redirect(action: "show", id: id)
		}
	}
	
	def download(){
		
		def trainSampleSearched = TrainSample.get(params.id)
		
		def webAppRootPath = servletContext.getRealPath("/")
		def file = new File(webAppRootPath, "/trainSample/${params.id}/"+trainSampleSearched.filename)
		
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

}
