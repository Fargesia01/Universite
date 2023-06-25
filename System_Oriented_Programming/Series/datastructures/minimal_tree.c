#include <stdlib.h>
#include <stdio.h>

typedef struct node 
{
    int nbr;
    int data;
    struct node **connects;
}		t_node;
 
t_node* newNode(int data)
{
  t_node* node = (t_node*)malloc(sizeof(t_node));
  if(node == NULL)
  	exit(1);
 
  node->data = data;
  node->connects = NULL;
  node->nbr = 0; 
  return(node);
}

void	connect(t_node *first, t_node *second)
{
	first->connects = realloc(first->connects, (first->nbr + 1) * sizeof(t_node*) );
	first->connects[first->nbr] = second;
	first->nbr++;
	second->connects = realloc(second->connects, (second->nbr + 1) * sizeof(t_node*));
	second->connects[second->nbr] = first;
	second->nbr++;
}
 
int main()
{
  t_node *root = newNode(1); 
  t_node *new = newNode(2);
  t_node *third = newNode(3);

  connect(root, new);
  connect(root, third);

  printf("%d\n", root->connects[0]->data);
  printf("%d\n", root->connects[1]->data);
  printf("%d\n", new->connects[0]->data);
  return 0;
}
