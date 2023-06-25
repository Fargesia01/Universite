#include <stdio.h>
#include <stdlib.h>

typedef struct s_linked_list
{
	int			data;
	struct s_linked_list	*next;
}		t_linked;

t_linked **head = NULL;

void swap()
{
	t_linked *tmp = *head;
	t_linked *tmp2 = (*head)->next->next;

	*head = (*head)->next;
	(*head)->next = tmp;
	(*head)->next->next = tmp2;
}

int main()
{
	t_linked *first = malloc(sizeof(t_linked));
	first->data = 1;
	t_linked *second = malloc(sizeof(t_linked));
	second->data = 2;
	second->next = first;
	t_linked *third = malloc(sizeof(t_linked));
	third->data = 3;
	third->next = second;

	head = &third;

	printf("%d\n", (*head)->data);
	printf("%d\n", (*head)->next->data);
	printf("%d\n", (*head)->next->next->data);
	printf("\n");

	swap(head);
	
	printf("%d\n", (*head)->data);
	printf("%d\n", (*head)->next->data);
	printf("%d\n", (*head)->next->next->data);
	printf("\n");
}
