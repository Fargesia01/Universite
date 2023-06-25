SRCS	= sop_gdb_stars.c

OBJS 	= $(SRCS:.c=.o)

NAME	= stars

LIB		= ar rcs

CC		= gcc -lm -g

RM		= rm -f

CFLAGS	= -Wall -Wextra 

.c.o:
	$(CC) $(CFLAGS) -c $< -o $(<:.c=.o)

$(NAME): $(OBJS)
	 $(CC) -o $(NAME) $(OBJS)

all:	$(NAME)

clean:
	$(RM) $(OBJS)

fclean:	clean
	$(RM) $(NAME)

re:		fclean all

.PHONY: all clean fclean re
