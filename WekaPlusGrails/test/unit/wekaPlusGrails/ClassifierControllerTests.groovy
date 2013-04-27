package wekaPlusGrails



import org.junit.*
import grails.test.mixin.*

@TestFor(ClassifierController)
@Mock(Classifier)
class ClassifierControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/classifier/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.classifierInstanceList.size() == 0
        assert model.classifierInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.classifierInstance != null
    }

    void testSave() {
        controller.save()

        assert model.classifierInstance != null
        assert view == '/classifier/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/classifier/show/1'
        assert controller.flash.message != null
        assert Classifier.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/classifier/list'

        populateValidParams(params)
        def classifier = new Classifier(params)

        assert classifier.save() != null

        params.id = classifier.id

        def model = controller.show()

        assert model.classifierInstance == classifier
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/classifier/list'

        populateValidParams(params)
        def classifier = new Classifier(params)

        assert classifier.save() != null

        params.id = classifier.id

        def model = controller.edit()

        assert model.classifierInstance == classifier
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/classifier/list'

        response.reset()

        populateValidParams(params)
        def classifier = new Classifier(params)

        assert classifier.save() != null

        // test invalid parameters in update
        params.id = classifier.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/classifier/edit"
        assert model.classifierInstance != null

        classifier.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/classifier/show/$classifier.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        classifier.clearErrors()

        populateValidParams(params)
        params.id = classifier.id
        params.version = -1
        controller.update()

        assert view == "/classifier/edit"
        assert model.classifierInstance != null
        assert model.classifierInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/classifier/list'

        response.reset()

        populateValidParams(params)
        def classifier = new Classifier(params)

        assert classifier.save() != null
        assert Classifier.count() == 1

        params.id = classifier.id

        controller.delete()

        assert Classifier.count() == 0
        assert Classifier.get(classifier.id) == null
        assert response.redirectedUrl == '/classifier/list'
    }
}
