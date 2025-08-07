package org.uob.a2.gameobjects;
// needed Use game object
import org.uob.a2.commands.Use;

public class Equipment extends GameObject implements Usable {

    protected UseInformation useInformation;
    protected String item1;
    protected String item2;
    protected Use useObject;

    public Equipment(String id, String name, String description, boolean hidden, UseInformation useInformation)
    {
        super(id, name, description, hidden);
        this.useInformation = useInformation;
        useObject = new Use("","");
    }

    // for combine items
    public Equipment(String id, String name, String description, boolean hidden, UseInformation useInformation, String item1, String item2)
    {
        super(id, name, description, hidden);
        this.useInformation = useInformation;
        this.item1 = item1;
        this.item2 = item2;
        useObject = new Use("","");
    }

    public UseInformation getUseInformation()
    {
        return useInformation;
    }

    public void setUseInformation( UseInformation useInformation)
    {
        this.useInformation = useInformation;
    }

    public String getItem1()
    {
        return item1;
    }

    public String getItem2()
    {
        return item2;
    }
    

        /**
     * Returns a string representation of this equipment, including the attributes inherited from {@code GameObject}
     * and the associated use information.
     *
     * @return a string describing the equipment
     */
 
    @Override
    public String toString() {
        return super.toString() + ", useInformation=" + useInformation;
    }

    public String use(GameObject target, GameState gameState)
    {
        String itemId = "";
        Item item = new Item("","","",false);
        
        if(target.getId().equals(useInformation.getTarget()))
        {
            //set result to not hidden
            itemId = useInformation.getResult();

            for (Item e : gameState.getMap().getCurrentRoom().getItems()) 
            {
                if(e.getId().equals(itemId))
                {
                    item = e;
                }
            }
             
            item.setHidden(false);

            return useInformation.getMessage();
        }
        else
        {
            return null;
        }
    }
}
