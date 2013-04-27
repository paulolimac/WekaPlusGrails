package wekaPlusGrails

class TrainSample {

	String name
	String description
	//byte[] arffFile
	String filename

	
	
	static constraints = {
		name(unique:false, nullable:false, blank:false)
		description(unique:false, nullable:false, blank:false, maxSize:500)
		//arffFile(nullable:false, unique:false, blank:false, maxSize:1024 * 1024 * 1) //1mb
		filename(nullable:true, blank:true)
	}

}
