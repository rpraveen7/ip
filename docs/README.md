# Veen Chatbot - User Guide

---
```
 __     __  ________  ________  __    __
|  \   |  \|        \|        \|  \  |  \
| \\   | \\| $$$$$$$$| $$$$$$$$| $$\ | $$
| $$   | $$| $$__    | $$__    | $$$\| $$
 \$$\ /  $$| $$  \   | $$  \   | $$$$\ $$
  \$$\  $$ | $$$$$   | $$$$$   | $$ \$$$$
   \$$ $$  | $$_____ | $$_____ | $$  \$$$
    \$$$   | $$     \| $$     \| $$   \$$
     \$     \$$$$$$$$ \$$$$$$$$ \$$    \$$
     
__________________________________________
Yo bro! I'm Veen
What can I do for you?

```

Veen is a lightweight, command-line interface (CLI) task manager designed to help you stay organized without any "bloat." It’s efficient, easy to use, and saves your tasks
automatically so you can pick up exactly where you left off.

---

## Quick Start

1.  Ensure you have **Java 17** or above installed.
2.  Download the latest `ip.jar` from my releases.
3.  Open your terminal/command prompt and navigate to the folder containing the file.
4.  Run the command: `java -jar ip.jar`
5.  Wait for the Veen logo to appear—you're ready to start managing tasks, bro!

---

## Features
### 1. Adding Tasks
Veen supports three types of tasks to cover all your needs.

#### **Todo**
Add a simple task without any deadline.
*   **Format:** `todo <description>`
*   **Example:** `todo return library book`

#### **Deadline**
Add a task with a specific "by" date or time.
*   **Format:** `deadline <description> /by <date/time>`
*   **Example:** `deadline submit assignment /by 2026-03-10 2359`
*   *Note: Veen recognizes dates in `yyyy-MM-dd` format!*

#### **Event**
Add a task with a start and end time.
*   **Format:** `event <description> /from <start> /to <end>`
*   **Example:** `event project meeting /from 2026-03-05 1400 /to 1600`

---
### 2. Viewing Your Tasks
See everything you have on your plate.
#### **List**
Displays all tasks currently in your list with their status.
*   **Command:** `list`
#### **Find**
Search for tasks by a specific keyword in their description.
*   **Format:** `find <keyword>`
*   **Example:** `find book`
#### **Find Date**
Search for all tasks happening on a specific date.
*   **Format:** `finddate <yyyy-MM-dd>`
*   **Example:** `finddate 2026-03-05`

---
### 3. Managing Tasks
Update your progress or clean up your list.
#### **Mark / Unmark**
Mark a task as completed or incomplete using its index number from the `list`.
*   **Format:** `mark <index>` or `unmark <index>`
*   **Example:** `mark 1`
#### **Delete**
Remove a task from your list permanently.
*   **Format:** `delete <index>`
*   **Example:** `delete 2`

---
### 4. Exiting Veen
Safely close the application.
*   **Command:** `bye`
*   *Note: Your tasks are automatically saved to `data/veen.txt` every time you make a change!*

---