# Model Color
# Table colors
class Color < ActiveRecord::Base
  after_validation :titleize_name

  validates :name, presence: true
  validates :hex_code, presence: true

  private

  def titleize_name
    self.name = name.mb_chars.downcase.to_s.titleize
  end
end
