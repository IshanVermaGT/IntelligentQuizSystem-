import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

public class IntelligentQuizSystem {

    static class Question {
        private String question;
        private String[] options;
        private int correctOption; // original index (1-4)
        private String difficulty;
        private int marks;

        public Question(String question, String[] options, int correctOption, String difficulty, int marks) {
            this.question = question;
            this.options = options;
            this.correctOption = correctOption;
            this.difficulty = difficulty;
            this.marks = marks;
        }

        public String getQuestion() {
            return question;
        }

        public String[] getOptions() {
            return options;
        }

        public int getCorrectOption() {
            return correctOption;
        }

        public String getDifficulty() {
            return difficulty;
        }

        public int getMarks() {
            return marks;
        }
    }

    static class ResultEntry {
        private String question;
        private String yourAnswer;
        private String correctAnswer;
        private String result;

        public ResultEntry(String question, String yourAnswer, String correctAnswer, String result) {
            this.question = question;
            this.yourAnswer = yourAnswer;
            this.correctAnswer = correctAnswer;
            this.result = result;
        }

        @Override
        public String toString() {
            return String.format("%-30s %-15s %-15s %-10s", question, yourAnswer, correctAnswer, result);
        }
    }

    static class Quiz {
        private List<Question> questions;
        private int score;
        private Scanner scanner;
        private List<ResultEntry> results;
        private Random rand;

        public Quiz() {
            questions = new ArrayList<>();
            score = 0;
            scanner = new Scanner(System.in);
            results = new ArrayList<>();
            rand = new Random();
            loadQuestions();
        }

        private void loadQuestions() {
            // 2 Easy questions - 20 marks each
            questions.add(new Question("What is 2 + 2?", new String[]{"3", "4", "5", "6"}, 2, "Easy", 20));
            questions.add(new Question("Which color is the sky?", new String[]{"Blue", "Green", "Red", "Yellow"}, 1, "Easy", 20));

            // 2 Medium questions - 30 marks each
            questions.add(new Question("Who wrote 'Romeo and Juliet'?", new String[]{"Shakespeare", "Hemingway", "Tolstoy", "Dickens"}, 1, "Medium", 30));
            questions.add(new Question("What is the capital of France?", new String[]{"Berlin", "Madrid", "Paris", "Rome"}, 3, "Medium", 30));

            // 2 Hard questions - 50 marks each
            questions.add(new Question("What is the derivative of sin(x)?", new String[]{"cos(x)", "-sin(x)", "-cos(x)", "sin(x)"}, 1, "Hard", 50));
            questions.add(new Question("Who developed the theory of relativity?", new String[]{"Newton", "Einstein", "Galileo", "Tesla"}, 2, "Hard", 50));

            // 2 Very Hard questions - 100 marks each
            questions.add(new Question("What year did World War I start?", new String[]{"1912", "1914", "1916", "1918"}, 2, "Very Hard", 100));
            questions.add(new Question("What is the capital of Iceland?", new String[]{"Oslo", "Stockholm", "Reykjavik", "Copenhagen"}, 3, "Very Hard", 100));
        }

        public void startQuiz() {
            System.out.println("Welcome to the Intelligent Quiz System!");
            String[] levels = {"Easy", "Medium", "Hard", "Very Hard"};
            int totalQuestions = 0;

            for (String level : levels) {
                int questionsAsked = 0;
                for (Question q : new ArrayList<>(questions)) {
                    if (q.getDifficulty().equalsIgnoreCase(level) && questionsAsked < 2) {
                        // Shuffle options and adjust correct answer index
                        List<String> optionList = new ArrayList<>();
                        Collections.addAll(optionList, q.getOptions());
                        String correctAnswer = q.getOptions()[q.getCorrectOption() - 1];
                        Collections.shuffle(optionList, rand);

                        int newCorrectIndex = optionList.indexOf(correctAnswer) + 1;

                        System.out.println("\nQuestion (" + level + " - " + q.getMarks() + " marks): " + q.getQuestion());
                        for (int j = 0; j < optionList.size(); j++) {
                            System.out.println((j + 1) + ". " + optionList.get(j));
                        }

                        System.out.print("Your answer (1-4): ");
                        int ans = getIntInput();

                        if (ans == newCorrectIndex) {
                            System.out.println(" Correct! +" + q.getMarks() + " marks");
                            score += q.getMarks();
                            results.add(new ResultEntry(shorten(q.getQuestion()), optionList.get(ans - 1), correctAnswer, "Correct"));
                        } else {
                            System.out.println(" Incorrect. The correct answer was: " + correctAnswer);
                            results.add(new ResultEntry(shorten(q.getQuestion()), optionList.get(ans - 1), correctAnswer, "Wrong"));
                        }

                        questions.remove(q);
                        questionsAsked++;
                        totalQuestions++;

                        if (totalQuestions >= 8) break;
                    }
                }
            }

            printFinalResults();
        }

        private void printFinalResults() {
            System.out.println("\n Quiz Over! Your total score is: " + score + " out of 400.");
            double percentage = (score / 400.0) * 100;
            System.out.printf("Your percentage: %.2f%%\n", percentage);
            printRemarks(percentage);

            System.out.print("\nDo you want to print the full marksheet? (yes/no): ");
            String choice = scanner.nextLine().trim().toLowerCase();
            if (choice.equals("yes") || choice.equals("y")) {
                printResultSheet();
            } else {
                System.out.println("Okay, thank you for taking the quiz! ");
            }
        }

        private void printResultSheet() {
            System.out.println("\n RESULT SHEET");
            System.out.printf("%-30s %-15s %-15s %-10s\n", "Question", "Your Answer", "Correct Answer", "Result");
            System.out.println("--------------------------------------------------------------------------------");
            for (ResultEntry entry : results) {
                System.out.println(entry);
            }
        }

        private void printRemarks(double percentage) {
            if (percentage >= 90) {
                System.out.println("Excellent performance! ");
            } else if (percentage >= 75) {
                System.out.println("Very good! ");
            } else if (percentage >= 50) {
                System.out.println("Good. Keep improving! ");
            } else {
                System.out.println("Needs more practice. Try again! ");
            }
        }

        private String shorten(String text) {
            return text.length() > 28 ? text.substring(0, 25) + "..." : text;
        }

        private int getIntInput() {
            while (true) {
                try {
                    int num = Integer.parseInt(scanner.nextLine());
                    if (num >= 1 && num <= 4) return num;
                    else System.out.print("Please enter a number between 1-4: ");
                } catch (NumberFormatException e) {
                    System.out.print("Invalid input. Enter number 1-4: ");
                }
            }
        }
    }

    public static void main(String[] args) {
        Quiz quiz = new Quiz();
        quiz.startQuiz();
    }
}