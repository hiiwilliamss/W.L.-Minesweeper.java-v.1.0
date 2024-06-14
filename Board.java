import java.util.Random;
import java.util.Scanner;

public class Board {
  private String[][] board;
  private String[][] playBoard;
  private boolean[][] visited;
  private final int N = 10;
  private boolean flag = true;

  public Board() {
    this.board = new String[10][10];
    this.playBoard = new String[10][10];
    this.visited = new boolean[10][10];
    Random rnd = new Random();
    Scanner sc = new Scanner(System.in);
  
    int x = 0;
    int y = 0;

    // create board, fill with either * or space
    for(int i = 0; i < 10; i++) {
        // random number from 0 to 10 (x coordinates)
        x = rnd.nextInt(10);
        // random number from 0 to 10 (y coordinates)
        y = rnd.nextInt(10);
        // if space is empty in board
        if(this.board[x][y] != "*") {
          this.board[x][y] = "*";
        }
        else { // redo loop
          i--;
        }
        
      }
      for (int r = 0; r < N; r++) {
        for (int c = 0; c < N; c++) {
          // if random value odd, dont add mine
          if(this.board[r][c] != "*") {
            this.board[r][c] = " ";
          }
        }
      }

    for(int i = 0; i < N; i++) {
      for(int j = 0; j < N; j++) {
        this.playBoard[i][j] = " ";
      }
    }
//Set values of "visited" to all false
    for(int i = 0; i < N; i++) {
      for(int j = 0; j < N; j++) {
        this.visited[i][j] = false;
      }
    }
    // traverse board checking for empty spots, aka " "
    for (int r = 0; r < N; r++) {
      for (int c = 0; c < N; c++) {
        // when empty spot " " found, check if neighbors are mines "*"
        if (this.board[r][c] == " ") {
          // total mine count
          int total = 0;
          // check from -1 to +1
          // outer loop: go from i-1 to i+1
          for (int i = r - 1; i < r + 2; i++) {
            // if neighbors are out of bounds
            if (i < 0 || i >= N) {
              continue;
            }
            // inner loop: go from j-1 to j+1
            for (int j = c - 1; j < c + 2; j++) {
              // if neighbors are out of bounds or current position
              if (j < 0 || j >= N || (r == i && c == j)) {
                continue;
              }
              // if neighbor is a mine, increment total + 1
              if (board[i][j] == "*") {
                total++;
              }
            }
            if(total == 0) {
              // change int 0 to string " "
              this.board[r][c] = " ";
            }
            else {
              // convert int to string to avoid incompatible type error into String [][]
              this.board[r][c] = String.valueOf(total);
            }
          }
        } else {
          // doSomethinger
          Print.print("");
        }
      }
    }
    /*
     * code goes here! you may choose to create methods and call but not required
     */
  }

  // setter
  // f_name: setBoard
  // returns: void
  // args: int r, int c

  
  // action
  public void print() {
    Print.println("  | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 0 |");
    String colStr;
    for (int r = 0; r < N; r++) {
      colStr = (r + 1) % 10 + " | ";
      for (int c = 0; c < N; c++)
        colStr += this.board[r][c] + " | ";
      Print.println(colStr);
    }
  }
 public void playBoard() {
    Print.println("");
    Print.println("  | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 0 |");
    String colStr;
    for (int r = 0; r < N; r++) {
      colStr = (r + 1) % 10 + " | ";
      for (int c = 0; c < N; c++)
        colStr += this.playBoard[r][c] + " | ";
      Print.println(colStr);
    }
  }
  //getter function for boolean flag
  public boolean getFlag(){
    return this.flag;
  }
  //setter function for boolean flag
  public void setFlag(boolean flag1){
    this.flag = flag1;
  }
  //user input function
  public void userInput(int r, int c, int t) {
    if(r == 0){r = 10;}
    if(c == 0){c = 10;}
    if (flag){
    switch(t) {
      case 1: // flag
        this.playBoard[(r - 1) % 10][(c - 1) % 10] = "X";
        break;
      case 2: // check for mines
        checkSpot(r,c);
        break;
      default:
        this.flag = false;
        break;
    }
    }
  }
  //checks if the game should end via victory at end of every turn
  public void checkWin(){
    int counter = 0;
  for(int r = 0;r < 10; r++){
    for(int c = 0;c < 10;c++){
      if(playBoard[r][c] == board[r][c] || board[r][c] == "*")
        counter++;
    }
  }    
  if (counter == 100){
    Print.println("You have won the game! congrats!");
    this.flag = false;
  }
  else
    Print.print("");



  }
  // check the spot if bomb, types a string accordingly
  public void checkSpot(int r, int c) {
    if(r == 0){r = 10;}
    if(c == 0){c = 10;}
    if(this.playBoard[(r - 1) % 10][(c - 1) % 10] == "X") {
      Print.print("its flagged");
    }
    else {
      if(this.board[(r - 1) % 10][(c - 1) % 10] == "*") {
        Print.print("you hit a bomb, you lose!\nHere was the board.");
        this.flag = false;
        
      }
      else{
        Print.print("");
        this.playBoard[r-1][c-1] = this.board[r-1][c-1];
      }
    }
  }
//"DFS" search code for the bloom part of part 2
public void checkNeighborNew(int r, int c){
  if(r >= 0 && c >= 0 && r <= 9 && c <= 9){
    if(this.visited[r][c] == false){
      this.visited[r][c] = true;
    
    if(this.board[r][c] == " "){
      this.playBoard[r][c] = "-"; 
    }
    else
      return;
    checkNeighborNew(r, c+1);
    checkNeighborNew(r, c-1);
    checkNeighborNew(r + 1, c);
    checkNeighborNew(r - 1, c);    
    }
  }
}
//2nd part of part 2, surrounds bloomed area with numbers
public void border() {
  for(int r = 0;r < 10; r++){
    for(int c = 0;c < 10;c++){
      try{
      if(playBoard[r][c] == "-" && playBoard[r+1][c] != "-"){
        playBoard[r+1][c] = board[r+1][c];
      }
      if(playBoard[r][c] == "-" && playBoard[r-1][c] != "-"){
        playBoard[r-1][c] = board[r-1][c];
      }
      if(playBoard[r][c] == "-" && playBoard[r][c+1] != "-"){
        playBoard[r][c+1] = board[r][c+1];
      }
      if(playBoard[r][c] == "-" && playBoard[r][c-1] != "-"){
        playBoard[r][c-1] = board[r][c-1];
      }
      if(playBoard[r][c] == "-" && playBoard[r+1][c+1] != "-"){
        playBoard[r+1][c+1] = board[r+1][c+1];
      }
      if(playBoard[r][c] == "-" && playBoard[r+1][c-1] != "-"){
        playBoard[r+1][c-1] = board[r+1][c-1];
      }
      if(playBoard[r][c] == "-" && playBoard[r-1][c+1] != "-"){
        playBoard[r-1][c+1] = board[r-1][c+1];
      }
      if(playBoard[r][c] == "-" && playBoard[r-1][c-1] != "-"){
        playBoard[r-1][c-1] = board[r-1][c-1];
      }
      }
      catch(Exception e){
        System.out.print("");
      }
      }


}
  }

//function to set border of full outer area to the numbers
/*
public void NeighborEdge(int r, int c){
  if (this.playBoard[r+1][c] != "O" && this.playBoard[r][c] == "O")
    this.playBoard[r+1][c] = this.board[r][c];

  if (this.playBoard[r-1][c] != "O" && this.playBoard[r][c] == "O")
    this.playBoard[r-1][c] = this.board[r][c];

  if (this.playBoard[r][c+1] != "O" && this.playBoard[r][c] == "O")
    this.playBoard[r][c+1] = this.board[r][c];

  if (this.playBoard[r][c-1] != "O" && this.playBoard[r][c] == "O")
    this.playBoard[r][c-1] = this.board[r][c];

  if (this.playBoard[r+1][c+1] != "O" && this.playBoard[r][c] == "O")
    this.playBoard[r+1][c+1] = this.board[r][c];

  if (this.playBoard[r-1][c+1] != "O" && this.playBoard[r][c] == "O")
    this.playBoard[r-1][c+1] = this.board[r][c];

  if (this.playBoard[r+1][c-1] != "O" && this.playBoard[r][c] == "O")
    this.playBoard[r+1][c-1] = this.board[r][c];

  if (this.playBoard[r-1][c-1] != "O" && this.playBoard[r][c] == "O")
    this.playBoard[r-1][c-1] = this.board[r][c];
  
}
*/
//R = row, C = col, I = how far off
//ATTEMPT AT FIXING FIRST PART OF PART 2, INCOMPLETE
public void ring(int r, int c){
//r,c is middle point,
//nested loop
boolean flag = true;
int i = 1;
int stop = 0;
while (flag){
  stop = 0;
  if (board[r][c] == " "){
    playBoard[r][c] = "-";
if(c+i < 10){
  if (board[r][c+i] == " "){
    playBoard[r][c+i] = "-";
    stop++;
  }
  else
    continue;
}

if(r+i < 10){
  if (board[r+i][c] == " "){
    playBoard[r+i][c] = "-";
    stop++;
  }
  else
    continue;
}

if(c-i > 0){
  if (board[r][c-i] == " "){
    playBoard[r][c-i] = "-";
    stop++;
  }
  else
    continue;
}

if(r-i > 0){
  if (board[r-i][c] == " "){
    playBoard[r-i][c] = "-";
    stop++;
  }
  else
    continue;
}

if(r+i < 10 && c+i < 10){
  if (board[r+i][c+i] == " "){
    playBoard[r+i][c+i] = "-";
    stop++;
  }
}

if(r-i > 0 && c+i < 10){
  if (board[r-i][c+i] == " "){
    playBoard[r-i][c+i] = "-";
    stop++;
  }
  else
    continue;
}

if(r+i < 10 && c-i > 0){
  if (board[r+i][c-i] == " "){
    playBoard[r+i][c-i] = "-";
    stop++;
  }
  else
    continue;
}

if(r-i > 0 && c-i > 0){
  if (board[r-i][c-i] == " "){
    playBoard[r-i][c-i] = "-";
    stop++;
  }
  else
    continue;
}
    if (stop < 8)
      i++;
    
  }//end of loop
}



}
}

