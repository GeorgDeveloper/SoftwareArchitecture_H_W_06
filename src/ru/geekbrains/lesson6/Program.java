package ru.geekbrains.lesson6;


import ru.geekbrains.lesson6.database.NotesDatabase;
import ru.geekbrains.lesson6.notes.core.application.ConcreteNoteEditor;
import ru.geekbrains.lesson6.notes.infrastructure.persistance.NotesDbContext;
import ru.geekbrains.lesson6.notes.presentation.queries.controllers.NotesController;
import ru.geekbrains.lesson6.notes.presentation.queries.views.NotesConsolePresenter;

public class Program {

    public static void main(String[] args) {
        NotesController controller = new NotesController(
                new ConcreteNoteEditor(new NotesDbContext(new NotesDatabase()), new NotesConsolePresenter()));
        controller.routeGetAll();

        System.out.println("*****************************************************");
        controller.routeAddNote("Новая запись", "Детали новой записи");
        controller.routeGetAll(); // Обновляем список после добавления записи

        System.out.println("*****************************************************");
        int noteIdToDelete = 1001; // Замените на ID записи, которую хотите удалить
        controller.routeRemoveNoteById(noteIdToDelete);
        controller.routeGetAll(); // Обновляем список после удаления записи

        System.out.println("*****************************************************");
        int noteIdToEdit = 1002; // Замените на ID записи, которую хотите изменить
        String newTitle = "Новый заголовок";
        String newDetails = "Новые детали записи";
        controller.routeEditNote(noteIdToEdit, newTitle, newDetails);
        controller.routeGetAll(); // Обновляем список после правки записи

    }

}
