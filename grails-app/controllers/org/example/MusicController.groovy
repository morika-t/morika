package org.example

import org.springframework.dao.DataIntegrityViolationException

class MusicController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
    def image(Long id) {
      def music = Music.get( id )
      if( music ){
        response.outputStream << music.jacket
      }else{
        response.sendError(404)
      }
    }

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [musicInstanceList: Music.list(params), musicInstanceTotal: Music.count()]
    }

    def create() {
        [musicInstance: new Music(params)]
    }

    def save() {
        def musicInstance = new Music(params)
        if (!musicInstance.save(flush: true)) {
            render(view: "create", model: [musicInstance: musicInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'music.label', default: 'Music'), musicInstance.id])
        redirect(action: "show", id: musicInstance.id)
    }

    def show(Long id) {
        def musicInstance = Music.get(id)
        if (!musicInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'music.label', default: 'Music'), id])
            redirect(action: "list")
            return
        }

        [musicInstance: musicInstance]
    }

    def edit(Long id) {
        def musicInstance = Music.get(id)
        if (!musicInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'music.label', default: 'Music'), id])
            redirect(action: "list")
            return
        }

        [musicInstance: musicInstance]
    }

    def update(Long id, Long version) {
        def musicInstance = Music.get(id)
        if (!musicInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'music.label', default: 'Music'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (musicInstance.version > version) {
                musicInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'music.label', default: 'Music')] as Object[],
                          "Another user has updated this Music while you were editing")
                render(view: "edit", model: [musicInstance: musicInstance])
                return
            }
        }

        musicInstance.properties = params

        if (!musicInstance.save(flush: true)) {
            render(view: "edit", model: [musicInstance: musicInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'music.label', default: 'Music'), musicInstance.id])
        redirect(action: "show", id: musicInstance.id)
    }

    def delete(Long id) {
        def musicInstance = Music.get(id)
        if (!musicInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'music.label', default: 'Music'), id])
            redirect(action: "list")
            return
        }

        try {
            musicInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'music.label', default: 'Music'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'music.label', default: 'Music'), id])
            redirect(action: "show", id: id)
        }
    }
}
