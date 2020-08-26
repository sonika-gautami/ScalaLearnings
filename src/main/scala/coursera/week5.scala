package coursera

class week5 {

/*

(xs ++ ys) map f = (xs map f) ++ (ys map f)


Base case:

lhs:
(Nil ++ ys) map f
ys map f

rhs:
(xs map f) ++ (ys map f)
(Nil map f) ++ (ys map f)
Nil ++ (ys map f)
(Nil ++ ys) map f
ys map f


Induction step:

(xs ++ ys) map f
((x :: xs) ++ ys) map f
(x :: (xs ++ ys)) map f
f(x) :: (xs ++ ys) map f


(xs map f) ++ (ys map f)
((x :: xs) map f) ++ (ys map f)
f(x) :: (xs map f) ++ (ys map f)

*/

}
