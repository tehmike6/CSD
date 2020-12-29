/***************************************************
 *                                                 *
 * file: among_us.c                                *
 *                                                 *
 * @Author  Skerdi Basha                           *
 * @Version 1-12-2020                              *
 * @email   sbash@csd.uoc.gr                       *
 *                                                 *
 * @brief   Implementation of among_us.h           *
 *                                                 *
 ***************************************************
 */

#include "among_us.h"

/* Global variable, represents an exception if a function returns and exception is 1 an error has occured in the function*/
int exception = 0;
/* Global variables, that control the HashTable*/
int a, b, p, m; // a and b are used in the hash function, p is the primes and m is the number of players.
/* Global variable, the Sentinel of the players tree */
struct Player *players_sentinel;
/* Global variable to the chain in the hash table that we last found a task*/
int ht_chain = 0;
/* Global pointer to the task that must be distributed*/
struct HT_Task *task_ht_ptr = NULL;
/* Global Task pointer stores the root of the task tree of the player thats being ejected.*/
struct Task* task_ptr =NULL;
/* Global Variable that controlls the print functions. Default value 1*/
int print_mode=1;
/*Global Variable Helper for array indexing in recursion*/
int cnt = 0;

unsigned int primes_g[650] = { 
                                 179,    181,    191,    193,    197,    199,    211,    223,    227,    229, 
                                 233,    239,    241,    251,    257,    263,    269,    271,    277,    281, 
                                 283,    293,    307,    311,    313,    317,    331,    337,    347,    349, 
                                 353,    359,    367,    373,    379,    383,    389,    397,    401,    409, 
                                 419,    421,    431,    433,    439,    443,    449,    457,    461,    463, 
                                 467,    479,    487,    491,    499,    503,    509,    521,    523,    541, 
                                 547,    557,    563,    569,    571,    577,    587,    593,    599,    601, 
                                 607,    613,    617,    619,    631,    641,    643,    647,    653,    659, 
                                 661,    673,    677,    683,    691,    701,    709,    719,    727,    733, 
                                 739,    743,    751,    757,    761,    769,    773,    787,    797,    809, 
                                 811,    821,    823,    827,    829,    839,    853,    857,    859,    863, 
                                 877,    881,    883,    887,    907,    911,    919,    929,    937,    941, 
                                 947,    953,    967,    971,    977,    983,    991,    997,   1009,   1013, 
                                1019,   1021,   1031,   1033,   1039,   1049,   1051,   1061,   1063,   1069, 
                                1087,   1091,   1093,   1097,   1103,   1109,   1117,   1123,   1129,   1151, 
                                1153,   1163,   1171,   1181,   1187,   1193,   1201,   1213,   1217,   1223, 
                                1229,   1231,   1237,   1249,   1259,   1277,   1279,   1283,   1289,   1291, 
                                1297,   1301,   1303,   1307,   1319,   1321,   1327,   1361,   1367,   1373, 
                                1381,   1399,   1409,   1423,   1427,   1429,   1433,   1439,   1447,   1451, 
                                1453,   1459,   1471,   1481,   1483,   1487,   1489,   1493,   1499,   1511, 
                                1523,   1531,   1543,   1549,   1553,   1559,   1567,   1571,   1579,   1583, 
                                1597,   1601,   1607,   1609,   1613,   1619,   1621,   1627,   1637,   1657, 
                                1663,   1667,   1669,   1693,   1697,   1699,   1709,   1721,   1723,   1733, 
                                1741,   1747,   1753,   1759,   1777,   1783,   1787,   1789,   1801,   1811, 
                                1823,   1831,   1847,   1861,   1867,   1871,   1873,   1877,   1879,   1889, 
                                1901,   1907,   1913,   1931,   1933,   1949,   1951,   1973,   1979,   1987, 
                                1993,   1997,   1999,   2003,   2011,   2017,   2027,   2029,   2039,   2053, 
                                2063,   2069,   2081,   2083,   2087,   2089,   2099,   2111,   2113,   2129, 
                                2131,   2137,   2141,   2143,   2153,   2161,   2179,   2203,   2207,   2213, 
                                2221,   2237,   2239,   2243,   2251,   2267,   2269,   2273,   2281,   2287, 
                                2293,   2297,   2309,   2311,   2333,   2339,   2341,   2347,   2351,   2357, 
                                2371,   2377,   2381,   2383,   2389,   2393,   2399,   2411,   2417,   2423, 
                                2437,   2441,   2447,   2459,   2467,   2473,   2477,   2503,   2521,   2531, 
                                2539,   2543,   2549,   2551,   2557,   2579,   2591,   2593,   2609,   2617, 
                                2621,   2633,   2647,   2657,   2659,   2663,   2671,   2677,   2683,   2687, 
                                2689,   2693,   2699,   2707,   2711,   2713,   2719,   2729,   2731,   2741, 
                                2749,   2753,   2767,   2777,   2789,   2791,   2797,   2801,   2803,   2819, 
                                2833,   2837,   2843,   2851,   2857,   2861,   2879,   2887,   2897,   2903, 
                                2909,   2917,   2927,   2939,   2953,   2957,   2963,   2969,   2971,   2999, 
                                3001,   3011,   3019,   3023,   3037,   3041,   3049,   3061,   3067,   3079, 
                                3083,   3089,   3109,   3119,   3121,   3137,   3163,   3167,   3169,   3181, 
                                3187,   3191,   3203,   3209,   3217,   3221,   3229,   3251,   3253,   3257, 
                                3259,   3271,   3299,   3301,   3307,   3313,   3319,   3323,   3329,   3331, 
                                3343,   3347,   3359,   3361,   3371,   3373,   3389,   3391,   3407,   3413, 
                                3433,   3449,   3457,   3461,   3463,   3467,   3469,   3491,   3499,   3511, 
                                3517,   3527,   3529,   3533,   3539,   3541,   3547,   3557,   3559,   3571, 
                                3581,   3583,   3593,   3607,   3613,   3617,   3623,   3631,   3637,   3643, 
                                3659,   3671,   3673,   3677,   3691,   3697,   3701,   3709,   3719,   3727, 
                                3733,   3739,   3761,   3767,   3769,   3779,   3793,   3797,   3803,   3821, 
                                3823,   3833,   3847,   3851,   3853,   3863,   3877,   3881,   3889,   3907, 
                                3911,   3917,   3919,   3923,   3929,   3931,   3943,   3947,   3967,   3989, 
                                4001,   4003,   4007,   4013,   4019,   4021,   4027,   4049,   4051,   4057, 
                                4073,   4079,   4091,   4093,   4099,   4111,   4127,   4129,   4133,   4139, 
                                4153,   4157,   4159,   4177,   4201,   4211,   4217,   4219,   4229,   4231, 
                                4241,   4243,   4253,   4259,   4261,   4271,   4273,   4283,   4289,   4297, 
                                4327,   4337,   4339,   4349,   4357,   4363,   4373,   4391,   4397,   4409, 
                                4421,   4423,   4441,   4447,   4451,   4457,   4463,   4481,   4483,   4493, 
                                4507,   4513,   4517,   4519,   4523,   4547,   4549,   4561,   4567,   4583, 
                                4591,   4597,   4603,   4621,   4637,   4639,   4643,   4649,   4651,   4657, 
                                4663,   4673,   4679,   4691,   4703,   4721,   4723,   4729,   4733,   4751, 
                                4759,   4783,   4787,   4789,   4793,   4799,   4801,   4813,   4817,   4831, 
                                4861,   4871,   4877,   4889,   4903,   4909,   4919,   4931,   4933,   4937, 
                                4943,   4951,   4957,   4967,   4969,   4973,   4987,   4993,   4999,   5003, 
                                5009,   5011,   5021,   5023,   5039,   5051,   5059,   5077,   5081,   5087, 
                                5099,   5101,   5107,   5113,   5119,   5147,   5153,   5167,   5171,   5179, 
                            };


/**
 * @brief Finds the prime that is greater than the max key value.
 * @param max_key The key that has the max value.
 * @return The prime that is greater than the max key value.
 * 
*/
static int findPrime(int max_key){
    int index = 0;
    while(max_key > primes_g[index]) index++;
    
    return primes_g[index];
}
/**
 * @brief Get a random int that is lower than ceiling and greater than floor.
*/
int getRandomInt(int floor,int ceiling){
    return ((rand() % (ceiling - floor + 1)) + floor);
}

/**
 * @brief Optional function to initialize data structures that 
 *        need initialization
 *
 * @return 1 on success
 *         0 on failure
 */
int initialize() {
    // Initialize the players
    players_tree = NULL;

    players_sentinel = (struct Player*) malloc(sizeof(struct Player));
    if(players_sentinel == NULL ) return 0;

    players_sentinel->evidence = -1;
    players_sentinel->is_alien = -1;
    players_sentinel->pid = -1;
    players_sentinel->parent = NULL;
    players_sentinel->lc = NULL;
    players_sentinel->rc = NULL;
    players_sentinel->tasks = NULL;

    //Initialize global variables for HashTable
    p = findPrime(max_tid_g);
    // Initialize the rand seed.
    srand(time(NULL));
    a = getRandomInt(1, p-1);
    b = getRandomInt(0, p-1);

    m = max_tasks_g;
    //Initialize the Hashtable
    general_tasks_ht.tasks = (struct HT_Task**)malloc(m * sizeof(struct HT_Task));
    general_tasks_ht.count = 0;
    int i;
    for(i=0; i<m; i++) general_tasks_ht.tasks[i] = NULL; // Set in every slot on the Hash Table NULL

    // Initialize Priority queue
    completed_tasks_pq.size = 0;
    completed_tasks_pq.tasks = (struct HT_Task*)malloc(m * sizeof(struct HT_Task));

    return 1;
}

/**
 * @brief Creates a new player node
 * 
 * @param pid The Player's id
 * 
 * @param is_alien The variable that decides if a player is alien or not.
 * @return Player pointer on success
 *         null on failure
 * 
*/
struct Player *newPlayer(int pid,int is_alien){
    struct Player *new=(struct Player*)malloc(sizeof(struct Player));
    if(new == NULL){
        exception = 1;
        return NULL;
    };

    new->is_alien = is_alien;
    new->pid = pid;
    new->evidence = 0;
    new->lc = players_sentinel;
    new->rc = players_sentinel;
    new->tasks = NULL;
    new->parent = NULL;
    
    return new;
}

/**
 * @brief Insert a player using recursion.
 * @param node The node that we are traversing.
 * @param pid The player's unique id.
 * @param is_alien The variable that decides if a player is alien or not.
 * @return A Player node.
 * 
 * 
*/
struct Player* insertPlayer(struct Player *node,int pid,int is_alien){

    // Base Case.
    if(node == players_sentinel){
        return newPlayer(pid,is_alien);
    }

    if(node->pid < pid) {
        node->rc = insertPlayer(node->rc, pid, is_alien);
        node->rc->parent = node;
    }
    else if(node->pid > pid){
        node->lc = insertPlayer(node->lc, pid ,is_alien);
        node->lc->parent = node;
    }
    return node;
}
/**
 * @brief Register Player
 *
 * @param pid The player's id
 *
 * @param is_alien The variable that decides if he is an alien or not
 * @return 1 on success
 *         0 on failure
 */
int register_player(int pid, int is_alien) {
    
    //Insert the player in the tree.
    //Check if he is the first.
    if(players_tree == NULL)
        players_tree = newPlayer(pid,is_alien);
    else
        insertPlayer(players_tree,pid,is_alien);
    //Check for exception if 1 then something went wrong abort.
    if(exception == 1){
        exception = 0;
        return 0;
    }
    print_players();

    return 1;
}

/**
 * @brief Hash function
 * @param key The key that based on it will return a different position.
 * @return A position in the hashtable based on the key.
*/
int hash(int key){
    return (((a*key + b) % p) % m);
}

/**
 * @brief Create a new task type of Struct HT_Task
 * @param tid The tid of the task
 * @param difficulty The difficulty of the task
 * @return The new Task or NULL if malloc failed
*/
struct HT_Task *newHTtask(int tid, int difficulty){
    struct HT_Task *task = (struct HT_Task*)malloc(sizeof(struct HT_Task));
    if(task == NULL){
        exception = 1;
        return NULL;   
    }
    task->tid = tid;
    task->difficulty = difficulty;
    task->next = NULL;
    return task;
}

/**
 * @brief Insert Task in the general task hash table
 *
 * @param tid The task id
 * 
 * @param difficulty The difficulty of the task
 *
 * @return 1 on success
 *         0 on failure
 */
int insert_task(int tid, int difficulty) {
    int position = hash(tid);
    struct HT_Task *task = newHTtask(tid,difficulty);
    if(exception == 1){ // If exception is 1 something went wrong abort.
        exception = 0;
        return 0;
    }

    if(general_tasks_ht.tasks[position] == NULL) // At the position there are no other tasks.
        general_tasks_ht.tasks[position] = task;
    else{ // At the position there are other tasks so we must insert at the start of the chain.
        task->next = general_tasks_ht.tasks[position];
        general_tasks_ht.tasks[position] = task;
    }
    general_tasks_ht.count++;
    print_tasks();
    return 1;
}

/**
 * @brief Create a new task type of Struct Task
 * @param tid The tid of the task
 * @param difficulty The difficulty of the task
 * @return The new Task or NULL if malloc failed
*/
struct Task* newTask(int tid, int difficulty){
    struct Task * new_task=(struct Task*)malloc(sizeof(struct Task));
    if(!new_task){
        exception = 1;
        return NULL;
    }
    new_task->tid=tid;
    new_task->difficulty=difficulty;
    new_task->lcnt=0;
    new_task->lc=NULL;
    new_task->rc=NULL;

    return new_task;
}

/**
 * @brief Peek a Task from the HT. The function traverses all the Hash Table.
 * @return The Task that the pointer points to and if all of the tasks have been peeked it returns NULL.
 * 
*/
struct Task* peekHTtask(){
    struct HT_Task* prev;
    while(task_ht_ptr == NULL){
        if(ht_chain == m-1) return NULL;
        task_ht_ptr = general_tasks_ht.tasks[++ht_chain];
    }
    
    prev = task_ht_ptr;
    task_ht_ptr = task_ht_ptr->next;
    return newTask(prev->tid,prev->difficulty);
}

/**
 * @brief Insert a Task in a Binary Search Tree.
 * @precondition The Tree must have a root.
 * @param root The root of the tree.
 * @param task The task to be inserted.
 * 
*/
struct Task* insertPlayerTask(struct Task* root,struct Task* task){

    if(root==NULL){
        return task;
    }

    if(root->tid>task->tid){
        root->lc = insertPlayerTask(root->lc,task);
        root->lcnt++;
    }else if(root->tid<task->tid){
        root->rc = insertPlayerTask(root->rc,task);
    }

    return root;
}

/**
 * @brief Insert a Task in a Binary Search Tree.
 * @param player The players tasks tree root.
 * @return 1 if successful
 *         0 if failed
 * 
*/
int registerPlayerTask(struct Player *player){
    if(player->is_alien) return 0;

    // Peek from the hash table to input the right Task
    struct Task* task = peekHTtask();
    if(task == NULL){
        exception = -1;// If exception is -1 then the Tasks from the hash table are over.
        return 0;
    }
    // If the root of the task tree is empty add the task.
    if(player->tasks==NULL){
        player->tasks = task;
    }else{
        // If not recursively add it.
        insertPlayerTask(player->tasks,task);
    }
    return 1;
}

/**
 * @brief Returns the next player node based on the direction.
 * @param node The player node that will return a child.
 * @param direction The choice of which child will be returned.
 * @return The child of node.
 * 
*/
struct Player* nextPlayer(struct Player *node, char direction){
    switch(direction){
        case 'r': 
            return node->rc;
        case 'l': 
            return node->lc;
        default:
            return NULL;
    }
}


/**
 * @brief Inorder Traversal of a Binary Search Tree
 * @param node A polymorphic pointer of tree node
 * @param delimiter A polymorphic pointer that points to where the traversal should stop.
 * @param visit A function which declares the actions that will be done upon visiting a node.
 * @param next_node A function that returns the next node that must be visited.
 * 
*/
void inorderTraversal(void* node, 
                       void* delimiter,
                       void* (*visit)(void* ),
                       void* (*next_node)(void* ,char ))
{
    if(node != delimiter){
        inorderTraversal(next_node(node,'l'),
                        delimiter,
                        visit,
                        next_node);
        visit(node);
        inorderTraversal(next_node(node,'r'),
                        delimiter,
                        visit,
                        next_node);
        
    }
}

/**
 * @brief Distribute Tasks to the players
 * @return 1 on success
 *         0 on failure
 */
int distribute_tasks() {
    // Initialize the Task pointer on hash table to start from the begging and reach the end.
    task_ht_ptr = general_tasks_ht.tasks[0];
    while(exception!=-1) // The hash table hasn't finished
        inorderTraversal(players_tree,players_sentinel,(void*)&registerPlayerTask,(void*)&nextPlayer);
    print_double_tree();
    task_ht_ptr = general_tasks_ht.tasks[0]; // At the end reasign to ptr the first position in the HT.

    return exception==-1; // If exception -1 then we have put all of the tasks.
}

/**
 * @brief Returns the player with the given pid.
 * @param root The root of the players tree.
 * @param pid The pid that we search in the tree.
 * @return The player with the given pid.
*/
struct Player* findPlayer(struct Player* root,int pid){
    if(root==players_sentinel) return root;

    if(root->pid>pid) return findPlayer(root->lc,pid);
    else if(root->pid<pid) return findPlayer(root->rc,pid);
    else return root;
}

/**
 * @brief Returns the left most node of the root
 * @param root The root of the tree that will be seached.
 * @return The left most node of the tree.
*/
struct Task* leftMostTask(struct Task* root){
    while(root->lc != NULL) root=root->lc;
    return root;
}

struct HT_Task *findHTtask(int tid){
    int pos = hash(tid);
    struct HT_Task *task = general_tasks_ht.tasks[pos];
    
    while(task->tid!=tid) task=task->next;
    return task;
}

/**
 * @brief Insert a task in priority queue
 * @param tid The tid of the task that is going to be inserted.
 * 
*/
int insertPriorityQueue(int tid){
    if(completed_tasks_pq.size == m) return 0;
    int i = completed_tasks_pq.size;
    struct HT_Task *new_task=findHTtask(tid);
    while(i>0 && new_task->difficulty > completed_tasks_pq.tasks[(i-1)/2].difficulty){
        completed_tasks_pq.tasks[i] = completed_tasks_pq.tasks[(i-1)/2];
        i = (i-1) / 2;
    }
    completed_tasks_pq.tasks[i] = *new_task;
    completed_tasks_pq.size++;
    return 1;
}

/**
 * @brief Deletes a Task from the Task tree of a player.
 * @param node The tree of the player.
 * @param tid The tid of the task that is going to be deleted
 * 
*/
struct Task* deletePlayerTask(struct Task* node,int tid){
    if(node == NULL) return node;

    if(node->tid>tid){
        node->lc = deletePlayerTask(node->lc,tid);
        if(!exception) // Signal that the task has been deleted.
            node->lcnt--;  // The task has been deleted and it was from the left of this node so count--;
    } 
    else if(node->tid<tid) {
       node->rc = deletePlayerTask(node->rc,tid);
    }

    else{
        exception = 0; // Signal that the node is deleted.
        // Case if the node is a leaf or has one child.
        if(node->lc == NULL || node->rc == NULL){
            struct Task* rc = node->rc;
            struct Task* lc = node->lc;
            insertPriorityQueue(node->tid);
            free(node);
            return rc!=NULL?rc:lc;
        }
        // Case if the node has two children. We find the min value of the right child to replace the parent.
        struct Task *left_most_node = leftMostTask(node->rc);
        

        node->tid = left_most_node->tid;
        node->difficulty = left_most_node->difficulty;
        node->rc = deletePlayerTask(node->rc,left_most_node->tid);
    }

    return node;
}

/**
 * @brief Implement Task
 *
 * @param pid The player's id
 *
 * @param tid The task's tid
 *
 * @return 1 on success
 *         0 on failure
 */
int implement_task(int pid, int tid) {
    exception = 1; // Flag that must be change in deletePlayerTask if a task was deleted.
    struct Player *player = findPlayer(players_tree,pid);
    player->tasks=deletePlayerTask(player->tasks,tid);
    if(exception == 1) return 0; // The task was not found so it wasnt deleted.

    print_double_tree();
    return 1;
}

/**
 * @brief Returns the left most node of the root
 * @param root The root of the tree that will be seached.
 * @return The left most node of the tree.
*/
struct Player* leftMostPlayer(struct Player* root){
    while(root->lc != players_sentinel) root=root->lc;
    return root;
}

/**
 * @brief Deletes a Player from the Player tree.
 * @param node The tree of the players.
 * @param pid The pid of the player that is going to be deleted.
 * 
*/
struct Player* deletePlayer(struct Player* node,int pid){
    if(node == players_sentinel) return node;

    if(node->pid>pid){
        node->lc = deletePlayer(node->lc,pid);
        node->lc->parent = node;
    } 
    else if(node->pid<pid) {
       node->rc = deletePlayer(node->rc,pid);
       node->rc->parent = node;
    }

    else{
        exception = 0;
        // Case if the node is a leaf or has one child.
        if(node->lc == players_sentinel || node->rc == players_sentinel){
            struct Player* rc = node->rc;
            struct Player* lc = node->lc;
            task_ptr = node->tasks;
            free(node);
            return rc!=players_sentinel?rc:lc;
        }
        // Case if the node has two children.
        struct Player *left_most_node = leftMostPlayer(node->rc);

        node->pid = left_most_node->pid;
        node->is_alien = left_most_node->is_alien;
        node->evidence = left_most_node->evidence;
        node->rc = deletePlayer(node->rc,left_most_node->pid);
    }

    return node;
}

/**
 * @brief Finds the number of tasks are in a tree by using the lcnt.
 * @param node The tree's root.
 * @return The number of tasks that are in the tree.
*/
int countOfTasks(struct Task *node){
    if(node==NULL) return 0;
    return countOfTasks(node->rc)+node->lcnt+1;
}

/**
 * @brief Turns a Binary Search Tree to a Table
 * @param root The tree's root.
 * @param A The table that the nodes of the tree will be inserted.
 * @post The postcondition is that the global variable cnt must be 0 when this function is called.
*/
void InsertInTable(struct Task* root,struct Task **A){
    if(root == NULL) return;
    InsertInTable(root->lc,A);
    A[cnt++] = root;
    InsertInTable(root->rc,A);
}

/**
 * @brief Merges two tables in to one and returns the new table.
 * @param A the table that will be merged with B.
 * @param B the table that will be merged with A.
 * @param A_size the size of A table.
 * @param B_size the size of B table.
 * @return The Merged Table.
*/
struct Task** mergeTables(struct Task* A[], struct Task* B[],int A_size, int B_size){
    struct Task **C = (struct Task**)malloc(A_size + B_size * sizeof(struct Task));
    if(!C) return NULL;

    int i=0,j=0,k=0;
    while(i<A_size && j<B_size){
        if( A[i]->tid <= B[j]->tid){
            C[k] = A[i++];
        }else{
            C[k] = B[j++];
        }
        C[k]->lcnt = 0;
        C[k]->rc = NULL;
        C[k]->lc = NULL;
        k++;
    }
    
    while(i < A_size){
        C[k] = A[i++];
        C[k]->lcnt = 0;
        C[k]->rc = NULL;
        C[k]->lc = NULL;
        k++;
    }
    while(j < B_size){
        C[k] = B[j++];
        C[k]->lcnt = 0;
        C[k]->rc = C[k]->lc = NULL;
        k++;
    }

    return C;
}

/**
 * @brief Turns a table to a binary search tree.
 * @param table The table that will be converted to a binary tree.
 * @param start Starting index of table.
 * @param last Ending index of table.
 * @return The root of the new tree.
*/
struct Task* tableToBST(struct Task** table, int start, int last)
{
    int mid = (start+last)/2;
    
    if(start>last)
        return NULL;
    
    struct Task* root = table[mid];
     
    if(start==last)
        return root;
    
    root->lc = tableToBST(table,start,mid-1);
    root->rc = tableToBST(table,mid+1,last);
    
   return root;
}

/**
 * @brief A function that given two BST roots it merges the trees in to one BST.
 * @param r1 The root of the first tree.
 * @param r2 The root of the second tree.
 * @return The root of the merged BST.
*/
struct Task* MergeTrees(struct Task* r1, struct Task* r2){
    int A_size = countOfTasks(r1);
    int B_size = countOfTasks(r2);

    struct Task** A_table;
    struct Task** B_table = (struct Task**)malloc(B_size* sizeof(struct Task));
    if(A_size > 0){ // If the A r1 tree has tasks.
        A_table = (struct Task**)malloc(A_size* sizeof(struct Task));
    }else return r2; // If not, We trasfer the root of the r2 to r1. 
    cnt = 0; // Needed to set the count for InsertInTable
    InsertInTable(r1,A_table);
    cnt = 0; // Needed to set the count for InsertInTable
    InsertInTable(r2,B_table);
    cnt = 0; // Needed to set the count for the next functions that use it.

    struct Task** C_table = mergeTables(A_table, B_table, A_size, B_size);

    return tableToBST(C_table, 0, A_size+B_size-1);
}

/**
 * @brief For a BST of tasks it sets the lcnt of tasks.
 * @param root The root of the task tree.
 * @return Used in the recursion.
*/
int setCountBST(struct Task* root){
    if(root == NULL) return 0;

    root->lcnt = setCountBST(root->lc);
    int rcnt = setCountBST(root->rc);

    return root->lcnt+rcnt+1;
}

/**
 * @brief Eject Player
 * 
 * @param pid_1 The ejected player's id
 *
 * @param pid_2 The crewmates id
 *
 * @return 1 on success
 *         0 on failure
 */
int eject_player(int pid_1, int pid_2) {
    struct Player* player = findPlayer(players_tree,pid_2);
    if(player == players_sentinel ) return 0;

    exception = 1; // Set the flag for deletePlayer if the player is deleted exception = 0;
    players_tree = deletePlayer(players_tree,pid_1);
    if(exception == 1) return 0;
    if(task_ptr == NULL){ // If the player thats being ejected has no tasks done.
        if(print_mode == 1) print_double_tree(); // Print mode is used to call eject in other functions and not print.
        return 1;
    } 
    
    player->tasks = MergeTrees(player->tasks,task_ptr); // merge the two task trees to one player
    setCountBST(player->tasks); // fixes the count for the tree

    if(print_mode == 1 ) print_double_tree();// Print mode is used to call eject in other functions and not print.

    return 1;
}

/**
 * @brief Witness Eject Player
 *
 * @param pid_1 The ejected player's id
 * 
 * @param pid_2 The crewmate's pid
 *
 * @param pid_a The alien's pid
 * 
 * @param number_of_witnesses The number of witnesses
 *
 * @return 1 on success
 *         0 on failure
 */
int witness_eject(int pid_1, int pid_2, int pid_a, int number_of_witnesses){
    print_mode = 2; // Now when ejected is called their will be no prints
    if(eject_player(pid_1,pid_2) == 0) return 0;
    findPlayer(players_tree,pid_a)->evidence = number_of_witnesses;
    
    print_double_tree();
    print_mode = 1;

    return 1;
}

/**
 * @brief Returns the player with the most evidence.
 * @return The player with most evidence.
*/
struct Player* max(struct Player* p1, struct Player* p2){
    if(p1 == players_sentinel || p2 == players_sentinel) {
        return p1==players_sentinel?p2:p1;
    }
    return p1->evidence>p2->evidence?p1:p2;
}

/**
 * @brief Finds the right most Player in the tree the maximum value.
 * @param root The tree's root
 * @return The player with most value in the tree.
*/
struct Player* rightMostPlayer(struct Player* root){
    while(root->rc != players_sentinel) root=root->rc;
    return root;
}

/**
 * @brief Finds the inorder predecessor of the given player node.
 * @param node The node that will find he's predecessor.
 * @return The predecessor of node.
*/
struct Player* findInorderPredecessor(struct Player* node){
    struct Player* left_most_player = leftMostPlayer(players_tree);
    if(node == left_most_player) return rightMostPlayer(players_tree);

    if(node->lc != players_sentinel) return rightMostPlayer(node->lc);

    struct Player* parent = node->parent;
    while(parent != NULL && node == parent->lc){
        node = parent;
        parent = parent->parent;
    }
    return parent;
}
/**
 * @brief Finds the inorder successor of the given player node.
 * @param node The node that will find he's successor.
 * @return The successor of node.
*/
struct Player* findInorderSuccessor(struct Player* node){
    struct Player* right_most_player = rightMostPlayer(players_tree);
    if(node == right_most_player) return leftMostPlayer(players_tree);

    if(node->rc != players_sentinel) return leftMostPlayer(node->rc);
    
    struct Player* parent = node->parent;
    while(parent != NULL && node == parent->rc){
        node = parent;
        parent = parent->parent;
    }
    return parent;
}

/**
 * @brief Deletes the task with most priority.
 * @return A Type Task that was created from the deleted task with most priority.
*/
struct Task *deletePriorityQueue(){
    if(completed_tasks_pq.size == 0) return NULL;
    struct HT_Task temp = completed_tasks_pq.tasks[0];
    int m=0,key=completed_tasks_pq.tasks[completed_tasks_pq.size-1].difficulty,p;
    completed_tasks_pq.size--;
    while((2*m+1 < completed_tasks_pq.size && key <= completed_tasks_pq.tasks[2 * m + 1].difficulty) ||
          (2*m+2 < completed_tasks_pq.size && key <= completed_tasks_pq.tasks[2 * m + 2].difficulty)){
        if(2*m+2 < completed_tasks_pq.size){
            if(completed_tasks_pq.tasks[2*m+1].difficulty >= completed_tasks_pq.tasks[2*m+2].difficulty){
                p = 2*m+1;
            }else{
                p = 2*m+2;
            }
        }else{
            p = completed_tasks_pq.size - 1;
        }
        completed_tasks_pq.tasks[m].difficulty = completed_tasks_pq.tasks[p].difficulty;
        completed_tasks_pq.tasks[m].tid = completed_tasks_pq.tasks[p].tid;
        m = p;
    }
    completed_tasks_pq.tasks[m].difficulty = key;
    completed_tasks_pq.tasks[m].tid = completed_tasks_pq.tasks[completed_tasks_pq.size].tid;
    return newTask(temp.tid,temp.difficulty);
}

/**
 * @brief Sabotage
 *
 * @param number_of_tasks The number of tasks to be sabotaged
 *
 * @param pid The player's id
 * 
 * @return 1 on success
 *         0 on failure
 */
int sabotage(int number_of_tasks, int pid) {
    if(completed_tasks_pq.size < 0) return 0;

    struct Player* player = findPlayer(players_tree,pid);
    if(player == players_sentinel) return 0;

    int index = number_of_tasks/2;
    while(index > 0 || !player->is_alien){
        player = findInorderPredecessor(player);
        index--;
    }
    while(number_of_tasks && completed_tasks_pq.size){
        player->tasks = insertPlayerTask(player->tasks,deletePriorityQueue());
        player = findInorderSuccessor(player);
        number_of_tasks--;
    }  
    print_double_tree();
    return 1;
}

/**
 * @brief Finds player with the most evidence.
 * @param node The root of the tree that will be searched.
 * @return The player with most evidence.
*/
struct Player* findMostEvidence(struct Player* node){

    if(node == players_sentinel) return players_sentinel;

    struct Player* lc = findMostEvidence(node->lc);
    struct Player* rc = findMostEvidence(node->rc);

    return max(max(lc,rc),node);
}

/**
 * @brief Vote
 *
 * @param pid_1 The suspicious player's id
 *
 * @param pid_2 The crewmate's pid
 * 
 * @param vote_evidence The vote's evidence
 *
 * @return 1 on success
 *         0 on failure
 */
int vote(int pid_1, int pid_2, int vote_evidence) {
    findPlayer(players_tree,pid_1)->evidence += vote_evidence;

    print_mode = 2; // Makes eject not to print.
    eject_player(findMostEvidence(players_tree)->pid,pid_2);

    print_double_tree();
    print_mode = 1;

    return 1;
}

/**
 * @brief Takes a root and splits it in half.
 * @post The cnt must be set to the count of nodes in tree / 2.
 * @param roots An array of two roots that will be set inside and will be used outside.
 * @return Used for the recursion.
*/
struct Task* splitBST(struct Task** roots){
   if(roots[0] == NULL) return NULL;

   if(roots[0]->lcnt == cnt){      
        struct Task* temp = roots[0]->lc;
        roots[0]->lc = NULL;
       if(roots[1]){
            return temp;
       }else{
            roots[1] = temp;
            return roots[0];
       }
       
   }

   else if(roots[0]->lcnt > cnt){
       roots[0]->lc = splitBST(roots);
       return roots[0];
   }else{
       cnt = cnt - roots[0]->lcnt - 1;
       roots[1] = roots[0];
       roots[1]->rc = splitBST(roots);
       return roots[1];
   }


}

/**
 * @brief Give Away Work
 *
 * @param pid_1 The existing crewmate's id
 *
 * @param pid_2 The new crewmate's pid
 *
 * @return 1 on success
 *         0 on failure
 */
int give_work(int pid_1, int pid_2) {
    return 0;
    if(register_player(pid_2,0) == 0) return 0;
    struct Player* player_1 = findPlayer(players_tree,pid_1);
    struct Player* player_2 = findPlayer(players_tree,pid_2);
    if(player_1 == players_sentinel || player_2 == players_sentinel) return 0;

    cnt = countOfTasks(player_1->tasks)/2;
    if(cnt < 1) return 1;

    struct Task** roots = (struct Task**)malloc(2*sizeof(struct Task));
    roots[0] = player_1->tasks;
    roots[1] = player_2->tasks;
    splitBST(roots);
    cnt = 0;
    setCountBST(player_1->tasks);
    setCountBST(player_2->tasks);
    print_double_tree();
    return 1;
}

/**
 * @brief Finds the count of aliens in a tree.
 * @param root The trees root.
 * @pre cnt must be set to 0.
 * @post cnt holds the number of aliens.
 * @return Used for the recursion.
*/
struct Player* countOfAliens(struct Player* root){

    if(root == players_sentinel) return root;

    countOfAliens(root->rc);
    countOfAliens(root->lc);

    if(root->is_alien) cnt++;

    return root;
}

/**
 * @brief Finds the count of players in a tree.
 * @param root The trees root.
 * @pre cnt must be set to 0.
 * @post cnt holds the number of Players.
 * @return Used for the recursion.
*/
struct Player* countOfPlayers(struct Player* root){

    if(root == players_sentinel) return root;

    countOfPlayers(root->rc);
    countOfPlayers(root->lc);

    if(!root->is_alien) cnt++;

    return root;
}

/**
 * @brief Terminate
 *
 * @return 1 on success
 *         0 on failure
 */
int terminate() {
    cnt = 0;
    countOfAliens(players_tree);
    int alien_count = cnt;
    cnt = 0;
    countOfPlayers(players_tree);
    int player_count = cnt;
    cnt = 0;

    if(alien_count > player_count){
        printf(" Aliens Win.");
    }
    if(!alien_count || completed_tasks_pq.size == general_tasks_ht.count){
        printf(" Crewmates Win.");
    }
    printf("\nDONE\n");
    return 1;
}

/**
 * @brief Visits a Player node and prints the players pid and is_alien.
 * @param node The node that will be visited.
 * 
*/
void visit_player(struct Player *node){
    printf("<%d:%d> ",node->pid,node->is_alien);
}

/**
 * @brief Print Players
 *
 * @return 1 on success
 *         0 on failure
 */
int print_players() {
    printf("Players=");
    inorderTraversal(players_tree,players_sentinel,(void*)&visit_player,(void*)&nextPlayer);
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
    int i;
    struct HT_Task *node;
    if(general_tasks_ht.count == 0) return 0;

    for(i=0; i<m; i++){
        printf(" Chain %d:",i+1);
        node = general_tasks_ht.tasks[i];
        while(node != NULL) {
            printf("<%d,%d>",node->tid,node->difficulty);
            node = node->next;
            if(node != NULL) printf(",");
        }
        printf("\n");
    }
    return 1;
}

/**
 * @brief Print Priority Queue
 *
 * @return 1 on success
 *         0 on failure
 */
int print_pq() {
    int i;
    printf(" Completed Tasks=");
    for(i=0;i<completed_tasks_pq.size;i++){
        printf("<%d,%d> ",completed_tasks_pq.tasks[i].tid,completed_tasks_pq.tasks[i].difficulty);
    }
    printf("\nDONE\n");
    return 1;
}

/**
 * @brief Visits a Task node and prints the task's tid and difficulty.
 * @param node The node that will be visited.
 * 
*/
void visit_task(struct Task *node){
    printf("<%d:%d:%d> ",node->tid,node->difficulty,node->lcnt);
}

/**
 * @brief Returns the next player node based on the direction.
 * @param node The player node that will return a child.
 * @param direction The choice of which child will be returned.
 * @return The child of node.
 * 
*/
struct Task* next_task(struct Task *node, char direction){
    switch(direction){
        case 'r': 
            return node->rc;
        case 'l': 
            return node->lc;
        default:
            return NULL;
    }
}

/**
 * @brief Visit a Player and print hes tasks.
 * @param node The node that will be visited.
*/
void visit_players(struct Player *node){
    printf(" Player%d=",node->pid);
    if(node->is_alien) printf("is_alien");
    else inorderTraversal(node->tasks,NULL,(void*)&visit_task,(void*)&next_task);
    printf("\n");
}

/**
 * @brief Visit a Player and print hes tasks and evidence.
 * @param node The node that will be visited.
*/
void visit_players_evidence(struct Player *node){
    printf(" <Player%d,evidence: %d> =",node->pid,node->evidence);
    if(node->is_alien) printf("is_alien");
    else inorderTraversal(node->tasks,NULL,(void*)&visit_task,(void*)&next_task);
    printf("\n");
}

/**
 * @brief Print Players & Task tree
 *
 * @return 1 on success
 *         0 on failure
 */
int print_double_tree() {
    if(print_mode == 1) inorderTraversal(players_tree,players_sentinel,(void*)&visit_players,(void*)&nextPlayer);
    if(print_mode == 2) inorderTraversal(players_tree,players_sentinel,(void*)&visit_players_evidence,(void*)&nextPlayer);
    return 1;
}

/**
 * @brief Free resources
 *
 * @return 1 on success
 *         0 on failure
 */

int free_all(void) {
    return 1;
}
