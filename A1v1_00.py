# assignment1
import zmq
import random
import time
import sys

flag = 0
if (len(sys.argv) > 1) :
    flag = sys.argv[1]



context = zmq.Context()
socketrep = context.socket(zmq.PAIR)
if flag:
    socketrep.bind("tcp://*:5452")
else:
    socketrep.connect("tcp://localhost:5452")
    y = int((random.random())*20 - 10)
    socketrep.send("%s"%y)


x = 1
while ( x == 1) :
    y = random.random()
    y = int(y*20 - 10)
    num = socketrep.recv()
    print("value generated : %s, value obtained %s" % (y, num))
    ans = int(num) + y
    print ("Value of sum : %s"% ans)
    socketrep.send("%s"% ans )
