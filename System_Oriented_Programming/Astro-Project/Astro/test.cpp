#include "Datastructure.h"

int	main()
{
	Datastructure *data = create_Datastructure();
	if (!data || !data->table_jsons)
	{
		write(2, "Error in create_Datastructure()\n", 32);
		exit(1);
	}

	for (int i = 0; i < MAX_JSONS + 5; i++)
		filling_datastructure((float)i, data);
	for (int i = 0; i < MAX_JSONS; i++)
	{
		if (!data->table_jsons[i])
		{
			write(2, "Error while filling Datastructure\n", 34);
			exit(1);
		}
	}

	destroy_Datastructure(data);
	if(data->table_jsons)
	{
		write(2, "Error while destroying Datastructure\n", 36);
		exit(1);
	}
}
