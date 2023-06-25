SRCS	= dice.c

HEAD	= push_swap.h

OBJS 	= $(SRCS:.c=.o)

NAME	= dice

LIB		= ar rcs

CC		= gcc

RM		= rm -f

CFLAGS	= -Wall -Wextra

.c.o:
	$(CC) $(CFLAGS) -c $< -o $(<:.c=.o) -I $(HEAD)

$(NAME): $(OBJS)
	 $(CC) -o $(NAME) $(OBJS)

all:	$(NAME)

clean:
	$(RM) $(OBJS)

fclean:	clean
	$(RM) $(NAME)

re:		fclean all

.PHONY: all clean fclean re
