package wekaPlusGrails

class TestSample {

	String name
	String description
	//byte[] arffFile
	String filename
	String evaluationTxt
	Classifier classifier

    static constraints = {
		name(unique:false, nullable:false, blank:false)
		description(unique:false, nullable:false, blank:false, maxSize:500)
		//arffFile(nullable:false, unique:false, blank:false, maxSize:1024 * 1024 * 1) //1mb
		filename(nullable:true, blank:true)
		evaluationTxt(nullable:true, blank: true, maxSize:500)
		classifier(unique:false, nullable:false, blank:false)
    }
	
}
