package home.application.teai_pracadomowatydzien8_2.controller;

import home.application.teai_pracadomowatydzien8_2.model.Note;
import home.application.teai_pracadomowatydzien8_2.repo.NoteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@EnableTransactionManagement
@Controller
public class NoteController {

    private NoteRepo noteRepo;

    @Autowired
    public NoteController(NoteRepo noteRepo) {
        this.noteRepo = noteRepo;
    }

    @GetMapping("/notes")
    public String getNotes(Model model) {
        model.addAttribute("notes", noteRepo.findAll());
        model.addAttribute("newNote", new Note());
        return "notes";
    }

    @GetMapping("/newNote")
    public String newNote(Model model) {
        model.addAttribute("newNote", new Note());
        return "newNote";
    }

    @PostMapping("/saveNewNote")
    public String saveNewNote(@ModelAttribute Note newNote) {
        noteRepo.save(newNote);
        return "redirect:/notes";
    }

    @GetMapping("/delete-note/{id}")
    public String deleteNote(@PathVariable Long id) {
        noteRepo.deleteById(id);
        return "redirect:/notes";
    }
    // wybieram notatkę do edycji
    @GetMapping("/edit-note/{id}")
    public String editNote(@PathVariable long id, Model model) {
        model.addAttribute("editNote", noteRepo.findById(id).get());
        return "editNote";
    }
    //modyfikuje a następnie zapisuje wybraną notatkę
    @PostMapping("/saveEditedNote")
    public String saveEditedNote(@ModelAttribute Note editNote) {
        Note note = new Note(editNote.getHeader(), editNote.getText());
        note.setId(editNote.getId());
        noteRepo.save(note);
        return "redirect:/notes";
    }

}
