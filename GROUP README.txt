Devendra and Cardy: Yeah I just found the problem.
Forgot to add a try and catch block in pawn.java for
the if(first){....}part of the move function. I am not sure
which chess to change so just add the try and catch 
block to whichever one you are working on and try
the scenario again.

Dionis:  me and Cardy discovered a problem 
with the pawn check in the game last night.
Apparently there seems to be some sort of 
a problem with when the pawn reaches the 
opponent's side and puts the king in check.
We tried to fix it but both of us are still
unsure of how this portion of the code works.
Look at the picture that I put in this 
directory.

Note: It has nothing to do with the code we 
just wrote and updated. The check for all 
other pieces work. All the other pawns work 
perfectly as well. Other pawns also still 
have the ability to check. It just seems 
that the code messes up in just this one 
specific scenario. (Look at picture.)
