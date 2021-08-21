package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var board:Array<Array<Button>>
    var boardValues=Array(3){IntArray(3)}
    var xTurn=true
    var movesCount=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        intialise()
        for(row in board){
            for(btn in row){
                btn.setOnClickListener {
                    updateMoves(it)
                }
            }
        }
        reset.setOnClickListener {
            intialise()
        }
    }

    private fun updateMoves(view: View) {
        when(view.id){
            R.id.button1->{
                updateValue(0,0,xTurn)
            }
            R.id.button2->{
                updateValue(0,1,xTurn)

            }
            R.id.button3->{
                updateValue(0,2,xTurn)
                updateValue(0,0,xTurn)

            }
            R.id.button4->{
                updateValue(1,0,xTurn)

            }
            R.id.button5->{
                updateValue(1,1,xTurn)

            }
            R.id.button6->{
                updateValue(1,2,xTurn)

            }
            R.id.button7->{
                updateValue(2,0,xTurn)

            }
            R.id.button8->{
                updateValue(2,1,xTurn)

            }
            R.id.button9->{
                updateValue(2,2,xTurn)

            }
        }
        xTurn=!xTurn;
        movesCount++;
        if(xTurn){
            updateStatus("Player X's turn");
        }
        else{
            updateStatus("Player O's turn")
        }
        //checking game status
        gameStatus(boardValues)
    }

    private fun gameStatus(boardValues: Array<IntArray>) {
        // checking draw state
        if(movesCount==9){
            updateStatus("It's a Draw")
            return;
        }
        var winner=""
        //checking rows
        for(i in 0..2){
            if(boardValues[i][0]==-1)continue
            if(boardValues[i][0]==boardValues[i][1] && boardValues[i][1]==boardValues[i][2]){
                if(boardValues[i][0]==1)
                {
                    winner="Player X Won"
                }
                else if(boardValues[i][0]==0){
                    winner="Player O won"
                }
                updateStatus(winner)
                disableButtons(board)
                return
            }
        }

        // checking columns
        for(i in 0..2){
            if(boardValues[0][i]==-1)continue
            if(boardValues[0][i]==boardValues[1][i] && boardValues[1][i]==boardValues[2][i]){

                if(boardValues[0][i]==1)
                {
                    winner="Player X Won"
                }
                else if(boardValues[0][i]==0){
                    winner="Player O won"
                }
                updateStatus(winner)
                disableButtons(board)
                return
            }
        }
        //checking left diagonal
        if(boardValues[0][0]!=-1 &&boardValues[0][0]==boardValues[1][1] && boardValues[1][1]==boardValues[2][2]){
            if(boardValues[0][0]==1)
            {
                winner="Player X Won"
            }
            else if(boardValues[0][0]==0){
                winner="Player O won"
            }
            updateStatus(winner)
            disableButtons(board)
            return
        }
        //checking right diagonal
        if(boardValues[0][2]!=-1 &&boardValues[0][2]==boardValues[1][1] && boardValues[1][1]==boardValues[2][0]){
            if(boardValues[0][2]==1)
            {
                winner="Player X Won"
            }
            else if(boardValues[0][2]==0){
                winner="Player O won"
            }
            updateStatus(winner)
            disableButtons(board)
            return
        }
    }

    private fun disableButtons(board: Array<Array<Button>>) {
        for(row in board){
            for(button in row){
                button.isEnabled=false
            }
        }
    }

    private fun updateStatus(status: String) {
        gameStatus.setText(status)
    }

    private fun updateValue(row: Int, col: Int, xTurn: Boolean) {
        var text=if(xTurn) "X" else "O"
        var value=if(xTurn) 1 else 0
        board[row][col].text=text
        board[row][col].isEnabled=false
        boardValues[row][col]=value


    }

    private fun intialise() {
        gameStatus.setText("Player X's turn")
        movesCount=0
        xTurn=true
        board= arrayOf(arrayOf(button1,button2,button3),arrayOf(button4,button5,button6),arrayOf(button7,button8,button9));
        for(i in 0..2){
            for(j in 0..2){
                boardValues[i][j]=-1;
                board[i][j].isEnabled=true
                board[i][j].text=""
            }
        }
    }

}