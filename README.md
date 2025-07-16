# Example Language #

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
