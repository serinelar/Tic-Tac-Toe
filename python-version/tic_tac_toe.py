import random

def print_board(board):
    for row in board:
        print("|".join(row))
        print("-" * 5)

def check_winner(board, player):
    # Check rows, columns, and diagonals for a win
    for row in board:
        if all(s == player for s in row):
            return True

    for col in range(3):
        if all(board[row][col] == player for row in range(3)):
            return True

    if all(board[i][i] == player for i in range(3)) or all(board[i][2 - i] == player for i in range(3)):
        return True

    return False

def check_draw(board):
    return all(cell != " " for row in board for cell in row)

def ai_move(board):
    # Simple AI: Random move
    while True:
        row, col = random.randint(0, 2), random.randint(0, 2)
        if board[row][col] == " ":
            return row, col

def get_player_move():
    while True:
        try:
            row, col = map(int, input("Enter your move (row and column, 1-3): ").split())
        except ValueError:
            print("Invalid input. Please enter two numbers.")
            continue
        if row < 1 or row > 3 or col < 1 or col > 3:
            print("Please select row and column between 1 and 3.")
        else:
            return row - 1, col - 1

def tic_tac_toe():
    # Initialize the board
    player_score = 0
    ai_score = 0

    while True:
        board = [[" " for _ in range(3)] for _ in range(3)]
        current_player = "X"

        while True:
            print_board(board)

            if current_player == "X":
                row, col = get_player_move()
                if board[row][col] != " ":
                    print("This cell is already taken. Try again.")
                    continue
            else:
                print("AI is making a move...")
                row, col = ai_move(board)

            board[row][col] = current_player

            if check_winner(board, current_player):
                print_board(board)
                if current_player == "X":
                    print("You win!")
                    player_score += 1
                else:
                    print("AI wins!")
                    ai_score += 1
                break

            if check_draw(board):
                print_board(board)
                print("It's a draw!")
                break

            current_player = "O" if current_player == "X" else "X"

        print(f"Scores: Player - {player_score}, AI - {ai_score}")
        replay = input("Do you want to play again? (y/n): ").lower()
        if replay != 'y':
            print("Thanks for playing!")
            break

        
       
if __name__ == "__main__":
    tic_tac_toe()
