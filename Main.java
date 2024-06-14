 import java.util.Scanner;
 class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    Board b = new Board();
    boolean flag = true;
    b.print();
    while(b.getFlag()) {
      b.playBoard();
      
      Print.print("row: ");
      int row = sc.nextInt();
      Print.print("column: ");
      int col = sc.nextInt();
      Print.print("Function(1 to flag, 2 to check): ");
      int f = sc.nextInt();

          try{
        b.userInput(row,col,f);
        //b.ring(row-1,col-1); THIS FUNCTION WAS AN ATTEMPTED FIX DOES NOT WORK
        b.checkNeighborNew(row-1, col-1); //this works but not for credit need new solution
        b.border();
        b.checkWin();
      }
      catch(Exception e){
        System.out.println("Input is out of range, select a row and column between 1 and 10" + e);
      }
    }
    Print.println("");
    b.print();
  }
}