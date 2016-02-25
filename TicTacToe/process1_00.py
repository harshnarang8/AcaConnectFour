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

    def buildTree(self,node,depth,a) :
        if depth != 0 :
            k = 0
            for i in range(3) :
                for j in range(3) :
                    flag1 = 0
                    flag2 = 0
                    for m in node.record[0] :#check saved possible future pos
                        if m == [i,j] :
                            flag1 = 1
                    for m in node.record[1] :#check saved possible future pos
                        if m == [i,j] :
                            flag2 = 1
                    if node.state[i][j] == 0 and (flag1!=1 and flag2!=1) :
                        node.child[k] = Tree()
                        node.child[k].parent = node
                        node.child[k].record = node.record[:]
                        node.child[k].record[(1-a)/2].append([i,j])
                        buildTree(self,node.child[k],depth-1,-1 * a)
        else :
            node.value = heurist(self, node, a)

    def heurist(self, node) :
        temp = self.board[:]
        temprow = self.row[:]
        tempcol =
        for m in node.record[0] :
            i,j = m
            temp[i][j] = a

        for m in node.record[1] :
            i,j = m
            temp[i][j] = -1 * a


    def term(self, node) :
        if node.value == NegInf or PosInf :
            return True
        else :
            return False

    def minimax(self, node, depth, maxPlayer) :
        if depth == 0 or self.term(node) :
            return node.value
        if maxPlayer :
            bestValue = NegInf
            for child in node.child #to be corrected
                v = self.minimax(self,child, depth - 1, False)
                bestValue = v if v > bestValue else bestValue
            return bestValue
        else :
            bestValue = PosInf
            for child in node.child #to be corrected
                v = self.minimax(self,child, depth - 1, True)
                bestValue = v if v < bestValue else bestValue
            return bestValue

    def NextMove(self) :
        # build the tree for evaluation
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
        self.board[x][y] = a
        self.col[y] += a
        self.row[x] += a
        if x == y :
            self.diag[1] += a
        if x + y == 2 :
            self.diag[2] += a
        pass

    def CheckWin(self):
        for m in self.row :
            if m == 3 :
                return "AI"
            elif m == -3 :
                return "Human"
        for m in self.col :
            if m == 3 :
                return "AI"
            elif m == -3 :
                return "Human"
        for m in self.diag :
            if m == 3 :
                return "AI"
            elif m == -3 :
                return "Human"

    def PrintBoard(self) :
        for i in range(3) :
            for j in range(3) :
                print self.board[i] ,


class Tree(object) :
    def __init__(self) :
        self.child = []
        self.parent = None
        self.value = None
        self.state = []
