using PokerFace.Data.Common;
using System.ComponentModel.DataAnnotations;

namespace PokerFace.Data.Entities
{
    public class Session
    {
        [Key]
        public int Id { get; set; }
        public string RoomId { get; set; }
        public int ModeratorId { get; set; }
        public DateTime LastLogin { get; set; }
    }
}
