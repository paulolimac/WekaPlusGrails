package wekaPlusGrails



import org.junit.*
import grails.test.mixin.*

@TestFor(LabelerController)
@Mock(Labeler)
class LabelerControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/labeler/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.labelerInstanceList.size() == 0
        assert model.labelerInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.labelerInstance != null
    }

    void testSave() {
        controller.save()

        assert model.labelerInstance != null
        assert view == '/labeler/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/labeler/show/1'
        assert controller.flash.message != null
        assert Labeler.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/labeler/list'

        populateValidParams(params)
        def labeler = new Labeler(params)

        assert labeler.save() != null

        params.id = labeler.id

        def model = controller.show()

        assert model.labelerInstance == labeler
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/labeler/list'

        populateValidParams(params)
        def labeler = new Labeler(params)

        assert labeler.save() != null

        params.id = labeler.id

        def model = controller.edit()

        assert model.labelerInstance == labeler
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/labeler/list'

        response.reset()

        populateValidParams(params)
        def labeler = new Labeler(params)

        assert labeler.save() != null

        // test invalid parameters in update
        params.id = labeler.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/labeler/edit"
        assert model.labelerInstance != null

        labeler.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/labeler/show/$labeler.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        labeler.clearErrors()

        populateValidParams(params)
        params.id = labeler.id
        params.version = -1
        controller.update()

        assert view == "/labeler/edit"
        assert model.labelerInstance != null
        assert model.labelerInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/labeler/list'

        response.reset()

        populateValidParams(params)
        def labeler = new Labeler(params)

        assert labeler.save() != null
        assert Labeler.count() == 1

        params.id = labeler.id

        controller.delete()

        assert Labeler.count() == 0
        assert Labeler.get(labeler.id) == null
        assert response.redirectedUrl == '/labeler/list'
    }
}
