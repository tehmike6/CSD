/***************************************************
 *                                                 *
 * file: among_us.c                                *
 *                                                 *
 * @Author  Antonios Peris                         *
 * @Version 20-10-2020                             *
 * @email   csdp1196@csd.uoc.gr                    *
 *                                                 *
 * @brief   Implementation of among_us.h           *
 *                                                 *
 ***************************************************
 */

#include "among_us.h"

/*Global Variables Sentinel for player list, Sentinel for Task list, Count for how many tasks are left from all players*/
struct Players *player_sentinel;
struct Tasks *task_sentinel;
int total_tasks = 0;

/**
* @brief This function Inserts a task in player's task List and keeps the list sorted.
* @param current, player that will receive a new task.
* @param t, task that will be inserted to player's task list.
*/
void insertTask(struct Players *current,struct Tasks *t){

	struct Tasks *transfer=current->tasks_head;
	while(1){
		if(transfer==current->tasks_sentinel){
			if(current->tasks_head==current->tasks_sentinel){
				current->tasks_head=t;
			}else{
				current->tasks_sentinel->next->next=t;
			}
			t->next=current->tasks_sentinel; 
			current->tasks_sentinel->next=t;
			
			break;
		}else if(t->difficulty<=transfer->next->difficulty){
			t->next=transfer->next;
			transfer->next=t;
			break;
		}else if(transfer->difficulty>=t->difficulty){
			if(transfer==current->tasks_head){
				t->next=transfer;
				current->tasks_head=t;
				break;
			}
		}
		transfer=transfer->next;
	}
}

/**
 * @brief Inserts an item of type struct Tasks in the stack.
 * @param temp, The item that will be inserted to the stack.
*/
void insertStack(struct Tasks *temp){
	temp->next=tasks_stack->head;
	tasks_stack->head=temp;
	tasks_stack->count++;
}

/**
 * @brief Pop's an item type of struct Tasks out of the stack.
 * @return Returns the top item in the stack type of struct Tasks.
*/
struct Tasks* pop(){
	struct Tasks *temp;
	if(tasks_stack->head==NULL){
		return NULL;
	}
	temp = tasks_stack->head;
	tasks_stack->head = tasks_stack->head->next;
	temp->next=NULL;
	return temp;
}

/**
 * @brief Moves the item type of struct Tasks from sourceRef to destRef
 * @param destRef, The destination that sourceRef will go.
 * @param sourceRef, The source node that will change place.
*/
void MoveTasks(struct Tasks** destRef, struct Tasks** sourceRef) 
{ 
    struct Tasks* newNode = *sourceRef; 
    *sourceRef = newNode->next; 
    newNode->next = *destRef; 
    *destRef = newNode; 
} 

/**
 * @brief Merges two task lists that are sorted.
 * @param p, Contains the task list that will store the Sorted Merged List.
 * @param b, Merges with p's task list.
 * @return The Sorted Merged List.
*/
struct Tasks* SortedMerge(struct Players* p, struct Tasks* b) 
{ 	
	
	if(p->tasks_sentinel==p->tasks_head)
		p->tasks_head=NULL;
	else
		p->tasks_sentinel->next->next=NULL;
    struct Tasks *a=p->tasks_head;
	struct Tasks newL; 	
    struct Tasks* tail = &newL;
    newL.next = NULL; 
    while (1) 
    { 
        if (a == NULL) 
        { 
            tail->next = b; 
            break; 
        } 
        else if (b == NULL) 
        { 
            tail->next = a; 
            break; 
        } 
        if (a->difficulty <= b->difficulty) 
            MoveTasks(&(tail->next), &a); 
        else
            MoveTasks(&(tail->next), &b); 
  
        tail = tail->next; 
    } 

	b=newL.next;
	while(b->next!=NULL){
		printf("%d ",b->tid);
		b=b->next;
	}
	p->tasks_sentinel->next=b;
	b->next=p->tasks_sentinel;
    return(newL.next); 
} 

/**
 * @brief Optional function to initialize data structures that 
 *        need initialization
 *
 * @return 1 on success
 *         0 on failure
 */
int initialize() {
	/*Initialize the sentinel of the Players List*/
	player_sentinel = (struct Players*)malloc(sizeof(struct Players));
	player_sentinel->pid=-1;
	player_sentinel->is_alien=-1;
	player_sentinel->evidence=-1;
	player_sentinel->next=player_sentinel;
	player_sentinel->prev=player_sentinel;

	/*Initialize the sentinel of the Tasks List*/
	task_sentinel= (struct Tasks*)malloc(sizeof(struct Tasks));
	task_sentinel->tid=-1;
	task_sentinel->difficulty=-1;
	task_sentinel->next=NULL;

	tasks_head=(struct Head_GL*)malloc(sizeof(struct Head_GL));
	tasks_head->tasks_count[0]=0;
	tasks_head->tasks_count[1]=0;
	tasks_head->tasks_count[2]=0;
	/*Initialize the heads*/
	players_head=player_sentinel;
	tasks_head->head=task_sentinel;
	
	tasks_stack=(struct Head_Completed_Task_Stack*)malloc(sizeof(struct Tasks));
	tasks_stack->count=0;
	tasks_stack->head=NULL;

    return 1;
}

/**
 * @brief Register player
 *
 * @param pid The player's id
 *
 * @param is_alien The variable that decides if he is an alien or not
 * @return 1 on success
 *         0 on failure
 */
int register_player(int pid,int is_alien) {
	struct Players *newPlayer = (struct Players*)malloc(sizeof(struct Players));
	if(!newPlayer) return 0;

	newPlayer->pid=pid;
	newPlayer->is_alien=is_alien;
	newPlayer->evidence=0;

	newPlayer->next=players_head->next;
	newPlayer->prev=player_sentinel;
	
	player_sentinel->next->prev=newPlayer;
	player_sentinel->next=newPlayer;

	newPlayer->tasks_sentinel = (struct Tasks*)malloc(sizeof(struct Tasks));
	if(!newPlayer->tasks_sentinel) return 0;
	newPlayer->tasks_sentinel->tid=-1;
	newPlayer->tasks_sentinel->difficulty=0;
	newPlayer->tasks_sentinel->next=NULL;

	newPlayer->tasks_head=newPlayer->tasks_sentinel;
	print_players();

    return 1;
}

/**
 * @brief Insert task in the general task list
 *
 * @param tid The task id
 * 
 * @param difficulty The difficulty of the task
 *
 * @return 1 on success
 *         0 on failure
 */
int insert_task(int tid,int difficulty) {
	struct Tasks *newTask = (struct Tasks*)malloc(sizeof(struct Tasks));
	if(newTask==NULL) return 0;

	newTask->tid=tid;
	newTask->difficulty=difficulty;

	struct Tasks *current;
	current = tasks_head->head;
	if(current==task_sentinel || current->difficulty>=difficulty){
		tasks_head->head=newTask;
		newTask->next=current;
	}else{
		while(current->next!=task_sentinel && current->next->difficulty < difficulty){
			current=current->next;
		}
		newTask->next=current->next;
		current->next=newTask;
	}
	tasks_head->tasks_count[difficulty-1]++;
	total_tasks++;

	print_tasks();
    return 1;
}

/**
 * @brief Distribute tasks to the players
 * @return 1 on success
 *         0 on failure
 */
int distribute_tasks() {
	struct Players *current=players_head->next;
	struct Tasks *temp=tasks_head->head;
	while(temp!=task_sentinel){
		if(current==player_sentinel || current->is_alien == 1){
			current=current->next;
			continue;
		}
		struct Tasks *copy = (struct Tasks*)malloc(sizeof(struct Tasks));
		if(!copy) return 0;

		copy->tid=temp->tid;
		copy->difficulty=temp->difficulty;

		insertTask(current,copy);

		
		temp=temp->next;
		current=current->next;
	}
	print_double_list();
    return 1;
}

/**
 * @brief Implement Task
 *
 * @param pid The player's id
 *
 * @param difficulty The task's difficuly
 *
 * @return 1 on success
 *         0 on failure
 */
int implement_task(int pid, int difficulty) {
	struct Players *current=players_head->next;
	while(current!=player_sentinel && current->pid!=pid){
		current=current->next;
	}
	if(current==player_sentinel) return 0;

	struct Tasks *temp=current->tasks_head;
	struct Tasks *prev=NULL;
	while(temp!=current->tasks_sentinel->next&&temp->next!=current->tasks_sentinel->next && temp->difficulty != difficulty){
		prev=temp;
		temp=temp->next;
	}
	if(temp==current->tasks_sentinel){
		return 0;
	}
	else if(temp==current->tasks_sentinel->next){
		current->tasks_head=current->tasks_sentinel;
		current->tasks_sentinel->next=current->tasks_sentinel;
		insertStack(temp);
	}else if(prev==NULL){
		current->tasks_head=temp->next;
		insertStack(temp);
	}else if(temp->next==current->tasks_sentinel->next){
		if(difficulty==temp->difficulty || temp->difficulty==temp->next->difficulty){
			prev->next=temp->next;
			insertStack(temp);
		}else{
			current->tasks_sentinel->next=temp;
			insertStack(temp->next);
			temp->next=current->tasks_sentinel;
		}
	}else{
		prev->next=temp->next;
		insertStack(temp);
	}
	print_double_list();
	total_tasks--;
    return 1;
}

/**
 * @brief Finds the player with least tasks.
 * @return Returns the player with least tasks type of struct Players.
*/
struct Players* getPlayerLeastTasks(int pid){
	struct Players *current=players_head->next,*p=players_head->next;
	struct Tasks *temp=current->tasks_head;
	int min=tasks_head->tasks_count[0] + tasks_head->tasks_count[1] + tasks_head->tasks_count[2];
	int i=0;
	while(current!= player_sentinel){
		if(current->pid==pid){
			current=current->next;
			continue;
		}
		temp = current->tasks_head;
		while(temp!=current->tasks_sentinel){
			temp=temp->next;
			i++;
		}
		if(i<min && current->is_alien!=1){
			min = i;
			p=current;
		}
		i=0;
		current=current->next;
	}
	return p;
}

/**
 * @brief Finds the player with most tasks.
 * @return Returns the player with most tasks type of struct Players.
*/
struct Players* getPlayerMostTasks(int pid){
	struct Players *current=players_head->next,*p=players_head->next;
	struct Tasks *temp=current->tasks_head;
	int max=0;
	int i=0;
	while(current!= player_sentinel){
		if(current->pid==pid){
			current=current->next;
			continue;
		}
		temp = current->tasks_head;
		while(temp!=current->tasks_sentinel){
			temp=temp->next;
			i++;
		}
		if(i>max && current->is_alien!=1){
			max = i;
			p=current;
		}
		i=0;
		current=current->next;
	}
	return p;
}

/**
 * @brief Eject Player
 * 
 * @param pid The ejected player's id
 *
 * @return 1 on success
 *         0 on failure
 */
int eject_player(int pid) {
	struct Players *min = getPlayerLeastTasks(pid);
	struct Players *current = players_head->next;
	while(current!=player_sentinel && current->pid!=pid){
		current=current->next;
	}
	if(current==player_sentinel) return 0;
	if(current->tasks_head==current->tasks_sentinel){
		current->prev->next=current->next;
		current->next->prev=current->prev;
		free(current->tasks_sentinel);
		free(current);
		print_double_list();
		return 1; 
	}
	struct Tasks *dead=current->tasks_head;
	current->tasks_sentinel->next->next=NULL;
	min->tasks_head=SortedMerge(min,dead);

	current->prev->next=current->next;
	current->next->prev=current->prev;
	free(current->tasks_sentinel);
	free(current);
	print_double_list();

    return 1;
}

/**
 * @brief Witness Eject Player
 *
 * @param pid_a The alien's pid
 * 
 * @param pid The crewmate's pid
 * 
 * @param number_of_witnesses The number of witnesses
 *
 * @return 1 on success
 *         0 on failure
 */
int witness_eject(int pid, int pid_a, int number_of_witnesses){

	eject_player(pid);

	struct Players *p=players_head->next;
	while(p!=player_sentinel && p->pid!=pid_a)
		p=p->next;
	p->evidence=number_of_witnesses;

    return 1;
}




/**
 * @brief Sabbotage
 *
 * @param pid The player's id
 *
 * @param number_of_tasks The number of tasks to be popped
 * 
 * @return 1 on success
 *         0 on failure
 */
int sabbotage(int pid, int number_of_tasks) {
    struct Players *current;
	int tasks_count=number_of_tasks;
    current = players_head->next;
	

	if(tasks_stack->count<number_of_tasks) return 0;

    while(current!=player_sentinel && current->pid!=pid){
    	current = current->next;
    }
	if(current==player_sentinel) return 0;

    int i = number_of_tasks/2;
    while(i>0){
		if(current==player_sentinel){
			current=current->prev;
			continue;
		}
    	current=current->prev;
    	i--;
    }
    if(current->is_alien || current==player_sentinel){
    	while(current->is_alien || current==player_sentinel){
    		current=current->next;
		}
    	insertTask(current,pop());
    	number_of_tasks--;
    }else{
    	insertTask(current,pop());
    	number_of_tasks--;
    }
	current=current->next;
    while(number_of_tasks){
    	if(current==player_sentinel || current->is_alien){
    		current=current->next;
    		continue;
		}
    	insertTask(current,pop());
		current=current->next;
    	number_of_tasks--;
    }
	print_double_list();
	total_tasks=total_tasks+tasks_count;
    return 1;
}


/**
 * @brief Vote
 *
 * @param pid The player's id
 * 
 * @param vote_evidence The vote's evidence
 *
 * @return 1 on success
 *         0 on failure
 */
int vote(int pid, int vote_evidence) {
    struct Players *current= players_head->next;
    while(current!=player_sentinel && current->pid != pid){
    	current=current->next;
    }
    current->evidence+=vote_evidence;
    int max= current->evidence;
    int new_pid=current->pid;

    current=players_head->next;
    while(current!=player_sentinel){
    	if(max < current->evidence){
    		max = current->evidence;
    		new_pid=current->pid;
    	}
		current=current->next;
    }
    eject_player(new_pid);


    return 1;
}


/**
 * @brief Give Away Work
 *
 * @return 1 on success
 *         0 on failure
 */
int give_work() {
	struct Players *min = getPlayerLeastTasks(-1);
	struct Players *max = getPlayerMostTasks(-1);

	struct Tasks *temp = max->tasks_head;
	int counter=0;

	if(min==max) return 0;

	while(temp!=max->tasks_sentinel){
		temp=temp->next;
		counter++;
	}
	int n_max=counter;
	n_max = n_max/2;

	if(n_max==0) return 0;

	temp=max->tasks_head;
	while(--n_max){
		temp=temp->next;
	}
	struct Tasks* maxTasks = max->tasks_head;
	
	max->tasks_head=temp->next;
	temp->next=NULL;
	min->tasks_head=SortedMerge(min,maxTasks);

	
	print_double_list();
    return 1;
}

/**
 * @brief Terminate
 *
 * @return 1 on success
 *         0 on failure
 */
int terminate() {
	struct Players *p=players_head->next;

	int player_count=0,alien_count=0;
	while(p!=player_sentinel){
		if(p->is_alien)
			alien_count++;
		else
			player_count++;
		p=p->next;
	}
	if(player_count < alien_count){
		printf("Aliens Win\n");
		return 1;
	}
	if(alien_count==0 || total_tasks==0){
		printf("Crewmates Win\n");
		return 1;
	}
	printf("DONE\n");
    return 0;
}

/**
 * @brief Print Players
 *
 * @return 1 on success
 *         0 on failure
 */
int print_players() {
    struct Players *p=players_head->next;
    printf("Players=");
    while(p!=player_sentinel){
		if(p!=players_head->next){
			printf(",");
		}
    	printf("<%d,%d>", p->pid,p->is_alien);
    	p=p->next;
    }
    printf("\nDONE\n");
    return 1;
}

/**
 * @brief Print Tasks
 *
 * @return 1 on success
 *         0 on failure
 */
int print_tasks() {
	struct Tasks *t=tasks_head->head;
	printf("List_Tasks=");
	while(t!=task_sentinel){
		if(t!=tasks_head->head){
			printf(",");
		}
		printf("<%d,%d>", t->tid,t->difficulty);
		t=t->next;
	}
	printf("\nDONE\n");
    return 1;
}

/**
 * @brief Print Stack
 *
 * @return 1 on success
 *         0 on failure
 */
int print_stack() {
    struct Tasks *t=tasks_stack->head;
    printf("Stack_Tasks=");
    while(t!=NULL){
		if(t!=tasks_stack->head){
			printf(",");
		}
    	printf("<%d,%d>", t->tid,t->difficulty);
    	t=t->next;
    }
	printf("\nDONE\n");
    return 1;
}

/**
 * @brief Print Players & Task List
 *
 * @return 1 on success
 *         0 on failure
 */
int print_double_list() {
	struct Players *p=players_head->next;
	struct Tasks *t;

	while(p!=player_sentinel){
		t=p->tasks_head;
		printf("Player%d=",p->pid);
		if(p->is_alien){
			printf("is_alien");
		}
		while(t!=p->tasks_sentinel){
			printf("<%d,%d>",t->tid,t->difficulty);
			t=t->next;
		}
		printf("\n");
		p=p->next;
	}
	printf("\nDONE\n");
    return 1;
}

/**
 * @brief Free resources
 *
 * @return 1 on success
 *         0 on failure
 */

int free_all(void) {
	struct Players *p=players_head->next,*p_del;
	struct Tasks *t,*t_del;
	
	//Free's the doubly linked list.
	while(p!=player_sentinel){
		t=p->tasks_head;
		while(t!=p->tasks_sentinel){
			t_del=t;
			t=t->next;
			free(t_del);
		}
		free(t);
		p_del=p;
		p=p->next;
		free(p_del);
	}
	free(player_sentinel);
	
	// Free's the General Tasks List
	t=tasks_head->head;
	while(t!=task_sentinel){
		t_del=t;
		t=t->next;
		free(t_del);
	}
	free(tasks_head);
	free(task_sentinel);
	
	// Free's the Stack
	t=tasks_stack->head;
	while(t!=NULL){
		t_del=t;
		t=t->next;
		free(t_del);
	}
	
    return 1;
}
