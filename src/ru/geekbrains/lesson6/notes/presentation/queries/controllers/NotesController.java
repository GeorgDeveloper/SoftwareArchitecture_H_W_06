package ru.geekbrains.lesson6.notes.presentation.queries.controllers;

import ru.geekbrains.lesson6.notes.core.application.interfaces.NoteEditor;
import ru.geekbrains.lesson6.notes.core.domain.Note;

import java.util.Date;
import java.util.Optional;
import java.util.stream.Stream;

public class NotesController extends Controller{

    private final NoteEditor notesEditor;

    public NotesController(NoteEditor notesEditor){
        this.notesEditor = notesEditor;
    }

    public void routeAddNote(String title, String details) {
        Note finalNote = getLastElement(this.notesEditor.getAll().stream());
        int newId = finalNote.getId() + 1;
        Note newNote = new Note(newId, 1, title, details, new Date()); // Создаем новую запись с временными значениями id и userId
        this.notesEditor.add(newNote);
    }

    public static <T> T getLastElement(Stream<T> stream) {
        return stream
                .reduce((e1, e2) -> e2)
                .orElse(null);
    }

    public void routeRemoveNote(Note note){
        this.notesEditor.remove(note);
    }

    public void routeGetAll(){
        this.notesEditor.printAll();
    }

    public void routeRemoveNoteById(int noteId) {
        Optional<Note> noteToRemove = this.notesEditor.getById(noteId);
        if (noteToRemove.isPresent()) {
            this.notesEditor.remove(noteToRemove.get());
            System.out.println("Запись удалена.");
        } else {
            System.out.println("Запись с указанным ID не найдена.");
        }
    }

    public void routeEditNote(int noteId, String newTitle, String newDetails) {
        Optional<Note> noteToEdit = this.notesEditor.getById(noteId);
        if (noteToEdit.isPresent()) {
            Note editedNote = noteToEdit.get();
            editedNote.setTitle(newTitle);
            editedNote.setDetails(newDetails);
            editedNote.setEditDate(new Date());
            this.notesEditor.edit(editedNote);
            System.out.println("Запись изменена.");
        } else {
            System.out.println("Запись с указанным ID не найдена.");
        }
    }

}
