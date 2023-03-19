using PokerFace.Data.Common;

namespace PokerFace.Data.SessionModels
{
    public class Session
    {
        public int Id { get; set; }
        public int RefId { get; set; }//id in db
        public string RoomId { get; set; }
        public int ModeratorId { get; set; }
        public List<int> CardIds { get; } = new List<int>();//active cards
        public SessionState State { get; set; }
    }
}
