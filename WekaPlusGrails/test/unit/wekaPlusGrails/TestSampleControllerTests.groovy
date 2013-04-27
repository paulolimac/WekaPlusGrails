package wekaPlusGrails



import org.junit.*
import grails.test.mixin.*

@TestFor(TestSampleController)
@Mock(TestSample)
class TestSampleControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/testSample/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.testSampleInstanceList.size() == 0
        assert model.testSampleInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.testSampleInstance != null
    }

    void testSave() {
        controller.save()

        assert model.testSampleInstance != null
        assert view == '/testSample/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/testSample/show/1'
        assert controller.flash.message != null
        assert TestSample.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/testSample/list'

        populateValidParams(params)
        def testSample = new TestSample(params)

        assert testSample.save() != null

        params.id = testSample.id

        def model = controller.show()

        assert model.testSampleInstance == testSample
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/testSample/list'

        populateValidParams(params)
        def testSample = new TestSample(params)

        assert testSample.save() != null

        params.id = testSample.id

        def model = controller.edit()

        assert model.testSampleInstance == testSample
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/testSample/list'

        response.reset()

        populateValidParams(params)
        def testSample = new TestSample(params)

        assert testSample.save() != null

        // test invalid parameters in update
        params.id = testSample.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/testSample/edit"
        assert model.testSampleInstance != null

        testSample.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/testSample/show/$testSample.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        testSample.clearErrors()

        populateValidParams(params)
        params.id = testSample.id
        params.version = -1
        controller.update()

        assert view == "/testSample/edit"
        assert model.testSampleInstance != null
        assert model.testSampleInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/testSample/list'

        response.reset()

        populateValidParams(params)
        def testSample = new TestSample(params)

        assert testSample.save() != null
        assert TestSample.count() == 1

        params.id = testSample.id

        controller.delete()

        assert TestSample.count() == 0
        assert TestSample.get(testSample.id) == null
        assert response.redirectedUrl == '/testSample/list'
    }
}
