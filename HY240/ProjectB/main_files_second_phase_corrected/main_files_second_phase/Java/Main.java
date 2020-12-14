/******************************************************
 *                                                    *
 * file: Main.java                                    *
 *                                                    *
 * @Author  Skerdi Basha                         	  *
 * @Version 1-12-2020                                 *
 * @email   sbash@csd.uoc.gr                          *
 *                                                    *
 * Source file for the needs of cs-240 project 2020   *
 *                                                    *
 ******************************************************
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    /*
     * 1) You need to create the classes that correspond to the C structs
     * 2) You need to create the global variables like, in the C header
     */

    /**
     * @brief Optional function to initialize data structures that 
     *        need initialization
     *
     * @return true on success
     *         false on failure
     */
    public static boolean initialize()
    {
        return true;
    }

    /**
     * @brief Register Player
     *
     * @param pid The player's id
     *
     * @param is_alien The variable that decides if he is an alien or not
     * @return true on success
     *         false on failure
     */
    public static boolean register_player(int pid, int is_alien) {
        return true;
    }

    
    /**
     * @brief Insert Task in the general task hash table
     *
     * @param tid The task id
     * 
     * @param difficulty The difficulty of the task
     *
     * @return true on success
     *         false on failure
     */
    public static boolean insert_task(int tid, int difficulty){
        return true;
    }

    /**
     * @brief Distribute Tasks to the players
     * @return true on success
     *         false on failure
     */
    public static boolean distribute_tasks() {
        return true;
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
    public static boolean implement_task(int pid, int tid) {
        return true;
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
    public static boolean eject_player(int pid_1, int pid_2) {
        return true;
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
    public static boolean witness_eject(int pid_1, int pid_2, int pid_a, int number_of_witnesses){
        return true;
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
    public static boolean sabotage(int number_of_tasks, int pid) {
        return true;
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
    public static boolean vote(int pid_1, int pid_2, int vote_evidence) {
        return true;
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
    public static boolean give_work(int pid_1, int pid_2) {
        return true;
    }

    /**
     * @brief Terminate
     *
     * @return true on success
     *         false on failure
     */
    public static boolean terminate() {
        return true;
    }

    /**
     * @brief Print Players
     *
     * @return 1 on success
     *         0 on failure
     */
    public static boolean print_players() {
        return true;
    }

    /**
     * @brief Print Tasks
     *
     * @return 1 on success
     *         0 on failure
     */
    public static boolean print_tasks() {
        return true;
    }

    /**
     * @brief Print Completed Task priority queue
     *
     * @return 1 on success
     *         0 on failure
     */
    public static boolean print_task_pq() {
        return true;
    }

    /**
     * @brief Print Players & Task tree
     *
     * @return 1 on success
     *         0 on failure
     */
    public static boolean print_double_tree() {
        return true;
    }
    
	/**
	 * @brief The main function
	 *
	 * @param argc Number of arguments
	 * @param argv Argument vector
	 *
	 * @return 0 on success
	 *         1 on failure
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException
	{
		BufferedReader inputFile;       /* Input file                          */
		String line;                    /* Data for eachline of the input file */
		String [] params;               /* Event parameters arguments          */

		/* Check command buff arguments */       
		if (args.length != 1) {
			System.err.println("Usage: <executable-name> <input_file>");
			System.exit(0);
		}

		/* Open input file */        
		inputFile = new BufferedReader(new FileReader(args[0]));

        int max_tasks_g = Integer.parseInt(inputFile.readLine());
		System.out.println("Max number of tasks: " + max_tasks_g);

		int max_tid_g = Integer.parseInt(inputFile.readLine());
		System.out.println("Max task tid: " + max_tid_g);

		/* Read input file and handle the events */
		while ((line = inputFile.readLine()) != null) {

			if (line.length() == 0) continue;

			System.out.println(">>> Event: " + line);
			params = line.split(" ");
			char eventID = line.charAt(0);

			switch(eventID) {

            /* Comment */
            case '#':
                break;

			/* Register Player
			 * P <pid><is_alien> */
			case 'P':
				{
					int pid = Integer.parseInt(params[1]);
					int is_alien = Integer.parseInt(params[2]);
                    
                    if (register_player(pid, is_alien)) {
                        System.out.println("P succeeded");
                    } else {
                        System.err.println("P failed");
					}

					break;
				}

            /* Insert Task
			 * T <tid><difficulty>  */
            case 'T':
                {
                    int tid = Integer.parseInt(params[1]);
                    int difficulty = Integer.parseInt(params[2]);

                    if (insert_task(tid, difficulty)) {
                        System.out.println("T succeeded");
                    } else {
                        System.err.println("T failed");
                    }

                    break;
                }

			/* Distribute Tasks
			 * D */
		    case 'D':
				{
					if (distribute_tasks()) {
                        System.out.println("D succeeded");
                    } else {
                        System.err.println("D failed");
                    }

					break;
				}

		    /* Implement Task
			 * I <pid> <difficulty> */
            case 'I':
				{
					int pid= Integer.parseInt(params[1]);
                    int tid = Integer.parseInt(params[2]);

					if (implement_task(pid, tid)) {
						System.out.println("I succeeded");
					} else {
						System.err.println("I failed");
					}

					break;
				}

		    /* Eject Player
			 * E <pid>*/
			case 'E':
				{
					int pid_1 = Integer.parseInt(params[1]);
                    int pid_2 = Integer.parseInt(params[2]);

					if (eject_player(pid_1, pid_2)) {
						System.out.println("E succeeded");
					} else {
						System.err.println("E failed");
					}

					break;
				}

                /* Witness Ejection
                 * W <pid> <pid_a> <number_of_witnesses>
                 */
            case 'W':
                {
					int pid_1 = Integer.parseInt(params[1]);
                    int pid_2 = Integer.parseInt(params[2]);
					int pid_a = Integer.parseInt(params[3]);
					int number_of_witnesses = Integer.parseInt(params[4]);

                    if (witness_eject(pid_1, pid_2, pid_a, number_of_witnesses)) {
						System.out.println("W succeeded");
					} else {
						System.err.println("W failed");
					}

                    break;
                }

			/* Sabotage
			 * S <number_of_tasks><pid> */
			case 'S':
				{
                    int number_of_tasks = Integer.parseInt(params[1]);
					int pid = Integer.parseInt(params[2]);

					if (sabotage(number_of_tasks, pid)) {
						System.out.println("S succeeded");
					} else {
						System.err.println("S failed");
					}

					break;
				}
			
            /* Vote
			 * V <pid> <vote_evidence> */
		    case 'V':
				{
					int pid_1 = Integer.parseInt(params[1]);
                    int pid_2 = Integer.parseInt(params[2]);
					int evidence = Integer.parseInt(params[3]);

					if (vote(pid_1, pid_2, evidence)) {
						System.out.println("V succeeded");
					} else {
						System.err.println("V failed");
					}

					break;
				}
            
			/* Give Away Work
			 * G */
			case 'G':
            {
                int pid_1 = Integer.parseInt(params[1]);
                int pid_2 = Integer.parseInt(params[2]);

                if (give_work(pid_1, pid_2)) {
                    System.out.println("G succeeded");
                } else {
                    System.err.println("G failed");
                }

                break;
            }
            /* Terminate
			 * F */
			case 'F':
            {
                if (terminate()) {
                    System.out.println("F succeeded");
                } else {
                    System.err.println("F failed");
                }

                break;
            }
			/* Print Players
			 * X */
			case 'X':
				{
					if (print_players()) {
						System.out.println("X succeeded");
					} else {
						System.err.println("X failed");
					}

					break;
				}

			/* Print Tasks
			 * Y */
			case 'Y':
				{
					if (print_tasks()) {
						System.out.println("Y succeeded");
					} else {
						System.err.println("Y failed");
					}

					break;
				}
            /* Print Completed Task priority queue
			 * Z */
			case 'Z':
            {
                if (print_task_pq()) {
                    System.out.println("Z succeeded");
                } else {
                    System.err.println("Z failed");
                }
                break;
            }
            /* Print Players & Task tree
			 * U */
			case 'U':
            {
                if (print_double_tree()) {
                    System.out.println("U succeeded");
                } else {
                    System.err.println("U failed");
                }
                break;
            }
                /* Empty line */
            case '\n':
                break;

                /* Ignore everything else */
            default:
                System.out.println("Ignoring " + line);
                break;
            }
        }
    }
}
