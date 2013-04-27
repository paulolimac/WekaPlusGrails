package wekaPlusGrails

class Labeler {

	String name
	String description
	
	String typeInput
	String unlabeledArffFilename
	String unlabeledTextFieldFilename
	
	Classifier classifier
	
	String labeledArffFilename
		
	static constraints = {
		name(unique:false, nullable:false, blank:false)
		description(unique:false, nullable:false, blank:false, maxSize:500)
		typeInput(blank:false, inList: ["textFieldInput", "arffFilenameInput"])
		unlabeledArffFilename(nullable:true, blank:true)
		unlabeledTextFieldFilename(nullable:true, blank:true, maxSize:10000)
		labeledArffFilename(nullable:true, blank:true)
	}
	

}
