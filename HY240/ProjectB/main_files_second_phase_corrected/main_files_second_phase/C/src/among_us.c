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

/*Global variable, represents an exception if a function returns and exception is 1 an error has occured in the function*/
int exception = 0;
/*Global variables, that control the HashTable*/
int a, b, p, m; // a and b are used in the hash function, p is the primes and m is the number of players.
/* Global variable, the Sentinel of the players tree */
struct Player *players_sentinel;

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
int find_prime(int max_key){
    int index = 0;
    while(max_key > primes_g[index]) index++;
    
    return primes_g[index];
}

int get_random_int(int floor,int ceiling){
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
    p = find_prime(max_tid_g);
    // Initialize the rand seed.
    srand(time(NULL));
    a = get_random_int(1, p-1);
    b = get_random_int(0, p-1);

    m = max_tasks_g;
    //Initialize the Hashtable
    general_tasks_ht.tasks = (struct HT_Task**)malloc(m * sizeof(struct HT_Task));
    general_tasks_ht.count = 0;
    int i;
    for(i=0; i<m; i++) general_tasks_ht.tasks[i] = NULL;

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
struct Player *new_player(int pid,int is_alien){
    struct Player *new=(struct Player*)malloc(sizeof(struct Player));
    if(new == NULL) exception = 1;

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
struct Player* insert_player(struct Player *node,int pid,int is_alien){

    if(node == players_sentinel){
        return new_player(pid,is_alien);
    }

    if(node->pid < pid) {
        node->rc = insert_player(node->rc, pid, is_alien);
        node->rc->parent = node;
    }
    else if(node->pid > pid){
        node->lc = insert_player(node->lc, pid ,is_alien);
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
        players_tree = new_player(pid,is_alien);
    else
        insert_player(players_tree,pid,is_alien);
    //Check for exception
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
    return 1;
}

/**
 * @brief Distribute Tasks to the players
 * @return 1 on success
 *         0 on failure
 */
int distribute_tasks() {
    return 1;
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
    return 1;
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
    return 1;
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
    return 1;
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
    return 1;
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
    return 1;
}

/**
 * @brief Terminate
 *
 * @return 1 on success
 *         0 on failure
 */
int terminate() {
    return 1;
}

/**
 * @brief Inorder Traversal of a Binary Search Tree
 * @param node An polymorphic pointer of tree node
 * @param delimiter An polymorphic pointer that points to where the traversal should stop.
 * @param visit A function which declares the actions that will be done upon visiting a node.
 * @param next_node A function that returns the next node that must be visited.
 * 
*/
void inorder_traversal(void* node, 
                        void* delimiter,
                        void (*visit)(void* ),
                        void* (*next_node)(void* ,char ))
{
    if(node != delimiter){
        inorder_traversal(next_node(node,'l'),
                        delimiter,
                        visit,
                        next_node);
        visit(node);
        inorder_traversal(next_node(node,'r'),
                        delimiter,
                        visit,
                        next_node);
        
    }
}

/**
 * @brief Visits a Player node and prints the players pid and is_alien.
 * @param node The node that will be visited.
 * 
*/
void visit_player(struct Player *node){
    printf("<pid%d:%d>",node->pid,node->is_alien);
}

/**
 * @brief Returns the next player node based on the direction.
 * @param node The player node that will return a child.
 * @param direction The choice of which child will be returned.
 * @return The child of node.
 * 
*/
struct Player* next_player(struct Player *node, char direction){
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
 * @brief Print Players
 *
 * @return 1 on success
 *         0 on failure
 */
int print_players() {
    printf("Players=");
    inorder_traversal(players_tree,players_sentinel,(void*)&visit_player,(void*)&next_player);
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
    return 1;
}

/**
 * @brief Print Priority Queue
 *
 * @return 1 on success
 *         0 on failure
 */
int print_pq() {
    return 1;
}

/**
 * @brief Print Players & Task tree
 *
 * @return 1 on success
 *         0 on failure
 */
int print_double_tree() {
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
