# TicTacToe v1.00
# Board represented by a 3 cross 3 matrix
# 0, 1, -1
#
#################

class AI(object) :

    def __init__(self) :
        self.board = [[0 for i in range(3)] for j in range(3)]
        pass

    def NextMove(self) :
        # Use MiniMax Algorithm for calculating next move
        # return best move
        x = 1
        y = 1
        pos = [ x, y ]
        return pos

    def makePlay(self,player,x,y) :
        a = 0
        if player == "AI" :
            a = 1
        elif player == "Human" :
            a = -1
        board[x][y] = a
        col[y] += a
        row[x] += a
        if x == y :
            diag[1] += a
        if x + y == 2 :
            diag[2] += a
        pass

    def CheckWin(player):
        if player == "AI" :
            pass
        elif player == "Human" :
            pass

    def PrintBoard(self) :
        for i in range(3) :
            for j in range(3) :
                print self.board[i] ,
