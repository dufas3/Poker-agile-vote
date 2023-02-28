namespace PokerFace.Data.Common
{
    public class BaseRepository
    {
        protected readonly ApplicationDbContext context;
        
        public BaseRepository(ApplicationDbContext context)
        {
            this.context = context;
        }

        public BaseRepository()
        {

        }
    }
}
