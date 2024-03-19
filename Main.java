import java.util.ArrayList;
import java.util.Scanner;
class Note {
  private String content;
  public Note(String content) {
    this.content = content;
  }
  public String getContent() {
    return content;
  }
}
class Notebook {
  private ArrayList<Note> notes;
  public Notebook() {
    this.notes = new ArrayList<Note>();
  }
  public void takeNewNote(String content) {
    notes.add(new Note(content));
  }
  public void deleteNote(int index) {
    if (index >= 0 && index < notes.size()) {
      notes.remove(index);
    } else {
      System.out.println("Invalid note index.");
    }
  }
  public ArrayList<Note> getNotes() {
    return notes;
  }
  public void saveToFile(String filename) {
    try {
      Scanner scanner = new Scanner(System.in);
      System.out.println("Enter your desired file name (excluding the .txt extension):");
      filename = scanner.nextLine() + ".txt";
      System.out.println("Saving notes to " + filename + "...");
      java.io.File file = new java.io.File(filename);
      java.io.FileWriter writer = new java.io.FileWriter(file);
      for (Note note : notes) {
        writer.write(note.getContent() + "\n");
      }
      writer.close();
      System.out.println("Notes saved successfully!");
    } catch (Exception e) {
      System.out.println("Error saving notes: " + e.getMessage());
    }
  }
}
public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Notebook notebook = new Notebook();
    int choice;
    do {
      System.out.println("\nSimple Note Taking App");
      System.out.println("1. Take new note");
      System.out.println("2. Delete note");
      System.out.println("3. View all notes");
      System.out.println("4. Save notes to file");
      System.out.println("5. Exit");
      System.out.print("Enter your choice: ");
      choice = scanner.nextInt();
      switch (choice) {
        case 1:
          System.out.print("Enter your note: ");
          scanner.nextLine(); // Consume newline character
          String content = scanner.nextLine();
          notebook.takeNewNote(content);
          System.out.println("Note added successfully!");
          break;
        case 2:
          System.out.print("Enter the index of the note to delete: ");
          int index = scanner.nextInt();
          notebook.deleteNote(index);
          break;
        case 3:
          System.out.println("\nYour notes:");
          int i = 1;
          for (Note note : notebook.getNotes()) {
            System.out.println(i + ". " + note.getContent());
            i++;
          }
          if (notebook.getNotes().isEmpty()) {
            System.out.println("  * No notes found.");
          }
          break;
        case 4:
          notebook.saveToFile("");
          break;
      }
    } while (choice != 5);
    System.out.println("Exiting program...");
  }
}
