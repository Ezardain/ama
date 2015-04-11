# Model Color
# Table colors
class Color < ActiveRecord::Base
  before_validation :add_style
  after_validation :titleize_name

  validates :name, presence: true
  validates :hex_code, presence: true
  validates :style, presence: true

  private

  def add_style
  	self.style = "background-color: " + hex_code
  end

  def titleize_name
    self.name = name.mb_chars.downcase.to_s.titleize
  end
end
