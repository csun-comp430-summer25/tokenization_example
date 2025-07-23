# Example Language #

## Abstract Grammar ##

```
op ::= `+` | `-` | `&&` | `<` | `==`
exp ::= INTEGER | IDENTIFIER | `true` | `false` | exp op exp
```

Tokens:
- `+`: `PlusToken`
- `-`: `MinusToken`
- `&&`: `LogicalAndToken`
- `<`: `LessThanToken`
- `==`: `DoubleEqualsToken`
- `INTEGER`: `IntegerToken(int)`
- `IDENTIFIER`: `IdentifierToken(String)`
- `true`: `TrueToken`
- `false`: `FalseToken`

AST Nodes:
- `+`: `PlusOp`
- `-`: `MinusOp`
- `&&`: `LogicalAndOp`
- `<`: `LessThanOp`
- `==`: `EqualsOp`
- `INTEGER`: `IntegerLiteralExp`
- `IDENTIFIER`: `IdentifierExp`
- `true`: `TrueLiteralExp`
- `false`: `FalseLiteralExp`
- `exp op exp`: `BinopExp`


## Concrete Grammar ##

```
primaryExp ::= INTEGER | `true` | `false` | `(` exp `)`
multExp ::= primaryExp ((`*` | `/`) primaryExp)*
addExp ::= multExp ((`+` | `-`) multExp)*
exp ::= addExp
``
