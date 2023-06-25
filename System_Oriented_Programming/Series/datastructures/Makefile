SRCS	= pointer_manipulation.c

HEAD	= datastructures.h

OBJS 	= $(SRCS:.c=.o)

NAME	= datastructure

LIB		= ar rcs

CC		= gcc -lm

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
