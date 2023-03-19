using System.ComponentModel.DataAnnotations;

namespace PokerFace.Data.Entities
{
    public class Moderator
    {
        [Key]
        public int Id { get; set; }
        public string? RoomId { get; set; }
        public string? Name { get; set; }
        public string? Password { get; set; }
        public string? ConnectionId { get; set; }
    }
}
