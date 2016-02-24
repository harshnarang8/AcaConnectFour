# TicTacToe v1.00
# Board represented by a 3 cross 3 matrix
# 0, 1, -1
#
#################

import Decimal

PosInf = Decimal('Infinity')
NegInf = Decimal('-Infinity')

class AI(object) :

    def __init__(self) :
        self.board = [[0 for i in range(3)] for j in range(3)]
        pass

    def minimax(self, node, depth, maxPlayer) :
        if depth == 0 or term(node) :
            return heuristic(node)

        if maxPlayer :
            bestValue = NegInf
            for each child of node #to be corrected
                v = self.minimax(self,child, depth - 1, False)
                bestValue = v if v > bestValue else bestValue
            return bestValue

        else :
            bestValue = PosInf
            for each cild of node #to be corrected
                v = self.minimax(self,child, depth - 1, True)
                bestValue = v if v < bestValue else bestValue
            return bestValue
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
