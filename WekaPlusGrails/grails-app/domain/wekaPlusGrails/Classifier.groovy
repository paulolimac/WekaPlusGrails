package wekaPlusGrails

class Classifier {
	
	String name
	String description
	//byte[] fileOfClassifier
	String filename
	String modelTxt
	String typeClassifier
	TrainSample trainSample

	static constraints = {
		name(unique:false, nullable:false, blank:false)
		description(unique:false, nullable:false, blank:false, maxSize:500)
		//fileOfClassifier(nullable:false, unique:false, blank:false, maxSize:1024 * 1024 * 1) //1mb
		filename(nullable:true, blank:true)
		modelTxt(nullable:true, blank: true)
		typeClassifier(blank:false, inList: ["j48", "jrip", "part"])
		trainSample(unique:false, nullable:false, blank:false)
	}
	
}
