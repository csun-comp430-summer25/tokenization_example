sealed trait Op
case object PlusOp extends Op
case object MinusOp extends Op
case object LogicalAndOp extends Op
case object LessThanOp extends Op
case object EqualsOp extends Op

sealed trait Exp
case class IntegerLiteralExp(value: Int) extends Exp
case class IdentifierExp(name: String) extends Exp
case object TrueLiteralExp extends Exp
case object FalseLiteralExp extends Exp
case class BinopExp(left: Exp, op: Op, right: Exp) extends Exp

// Rust:
// enum Op {
//   PlusOp, MinusOp, LogicalAndOp, LessThanOp, EqualsOp
// }
//
// enum Exp {
//   IntegerLiteralExp(i32),
//   IdentifierExp(String),
//   TrueLiteralExp,
//   FalseLiteralExp,
//   BinopExp(Exp, Op, Exp)
// }
