#ifndef DATASTRUCTURES_H
#define DATASTRUCTURES_H

#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <string.h>

typedef struct s_linked_list
{
	int		data;
	t_linked	*next;
}		t_linked;

typedef struct s_double_linked_list
{
	int		data;
	t_double	*next;
	t_double	*prev;
}		t_double;

typedef struct s_binary_tree
{
	int		data;
	t_binary	*right;
	t_binary	*left;
}		t_binary;

typedef struct s_fifo
{
	
}
