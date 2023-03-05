using System.ComponentModel.DataAnnotations;

namespace PokerFace.Data.Entities
{
    public class User
    {
        [Key]
        public int Id { get; set; }
        public int? RoomId { get; set; }
        public string? Name { get; set; }
        public string? Password { get; set; }
        public int? SelectedCardId { get; set; }
        public string ConnectionId { get; set; }
    }
}
