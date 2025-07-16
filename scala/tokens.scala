sealed trait Token
case object PlusToken extends Token
case object MinusToken extends Token
case class IntegerToken(value: Int) extends Token
case class IdentifierToken(name: String) extends Token
