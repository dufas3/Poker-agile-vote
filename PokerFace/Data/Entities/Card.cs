using System.ComponentModel.DataAnnotations;

namespace PokerFace.Data.Entities
{
    public class Card
    {
        [Key]
        public int Id { get; set; }
        public string Value { get; set; }
        public bool IsActive { get; set; }
    }
}
