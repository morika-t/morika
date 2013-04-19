package org.example



import org.junit.*
import grails.test.mixin.*

@TestFor(MusicController)
@Mock(Music)
class MusicControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/music/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.musicInstanceList.size() == 0
        assert model.musicInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.musicInstance != null
    }

    void testSave() {
        controller.save()

        assert model.musicInstance != null
        assert view == '/music/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/music/show/1'
        assert controller.flash.message != null
        assert Music.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/music/list'

        populateValidParams(params)
        def music = new Music(params)

        assert music.save() != null

        params.id = music.id

        def model = controller.show()

        assert model.musicInstance == music
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/music/list'

        populateValidParams(params)
        def music = new Music(params)

        assert music.save() != null

        params.id = music.id

        def model = controller.edit()

        assert model.musicInstance == music
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/music/list'

        response.reset()

        populateValidParams(params)
        def music = new Music(params)

        assert music.save() != null

        // test invalid parameters in update
        params.id = music.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/music/edit"
        assert model.musicInstance != null

        music.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/music/show/$music.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        music.clearErrors()

        populateValidParams(params)
        params.id = music.id
        params.version = -1
        controller.update()

        assert view == "/music/edit"
        assert model.musicInstance != null
        assert model.musicInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/music/list'

        response.reset()

        populateValidParams(params)
        def music = new Music(params)

        assert music.save() != null
        assert Music.count() == 1

        params.id = music.id

        controller.delete()

        assert Music.count() == 0
        assert Music.get(music.id) == null
        assert response.redirectedUrl == '/music/list'
    }
}
