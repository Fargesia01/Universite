#include <stdio.h>
#include <stdlib.h>
#include <limits.h>

typedef struct s_stackNode
{
    int data;
    struct s_stackNode* next;
}		t_stackNode;

typedef struct s_queue
{
	t_stackNode	*first;
	t_stackNode	*end;
}		t_queue;
 
t_stackNode* newNode(int data)
{
    t_stackNode* stackNode = (t_stackNode*) malloc(sizeof(t_stackNode));
    if(stackNode == NULL)
    {
        fprintf(stderr, "Couldn't allocate memory\n");
        exit(1);
    }
    stackNode->data = data;
    stackNode->next = NULL;
    return stackNode;
}
 
int isEmpty(t_queue root)
{
    return !root.first;
}

void push(t_queue* root, int data)
{
    t_stackNode* stackNode = newNode(data);
    if (!isEmpty(*root))
    	stackNode->next = root->first;
    else
    {
	stackNode->next = NULL;
	root->end = stackNode;
    }
    root->first = stackNode;
    printf("%d pushed to queue\n", data);
}
 
int pop(t_queue* root)
{
    if (isEmpty(*root))
    {
        fprintf(stderr, "Popped an empty queue\n");
        exit(2);
    }
    int popped = root->end->data;
    t_stackNode* tmp = root->first;
    while (tmp->next->next != NULL)
	tmp = tmp->next;
    tmp->next = NULL;
    free(root->end);
    root->end = tmp;
    printf("%d popped from the queue\n", popped);
    return (popped);
}
 
int peek(t_queue root)
{
    if (isEmpty(root))
    {
        printf("queue is empty\n");
        return INT_MIN;
    }
    printf("%d peeked from queue\n", root.end->data);
    return root.end->data;
}
 
int main()
{
    t_queue root;
 
    peek(root);
    push(&root, 10);
    push(&root, 20);
    push(&root, 30);
    pop(&root);
    peek(root);
 
    return 0;
}
