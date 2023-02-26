using System.ComponentModel.DataAnnotations;

namespace PokerFace.Data.Entities
{
    public class Session
    {
        [Key]
        public int Id { get; set; }
        public int RoomId { get; set; }
        public int ModeratorId { get; set; }
        public List<int> UserIds { get; } = new List<int>();
        public List<int> CardIds { get; } = new List<int>();
    }
}
