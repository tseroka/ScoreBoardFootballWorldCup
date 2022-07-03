Business logic assumptions:
- Score of team cannot be decreased while playing game
- Team cannot play more than one game at the time
- No restrictions on teams' names except not blank

Technical assumptions:
- For sake of simplicity, no external date time provider is used to assign game started time
- Lombok was not used on purpose
- Library is not thread-safe
- No persistence is provided
- For sake of simplicity, no custom exceptions were used
