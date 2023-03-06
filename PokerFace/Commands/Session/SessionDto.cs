using PokerFace.Data.Common;

namespace PokerFace.Commands.Session
{
    public class SessionDto
    {
        public string RoomId { get; set; }
        public List<Data.Entities.User> Users { get; } = new List<Data.Entities.User>();
        public List<Data.Entities.Card> Cards { get; } = new List<Data.Entities.Card>();
        public SessionState State { get; set; }
    }
}
